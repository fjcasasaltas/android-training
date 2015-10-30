package com.fjcasasaltas.services;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.fjcasasaltas.services.ActualizacionBoundService.ActualizacionBinder;
import com.fjcasasaltas.services.data.DaoFactory;
import com.fjcasasaltas.services.model.Contador;
import com.fjcasasaltas.services.model.dao.ContadorDao;

public class MainActivity extends Activity implements OnClickListener{
	
	private ActualizacionBoundService mService;
	private boolean mBound;
	
	private ServiceConnection mConnection = new ServiceConnection() {
	    // LLamado al conectar el servicio
	    public void onServiceConnected(ComponentName className, IBinder service) {
	        ActualizacionBinder binder = (ActualizacionBinder) service;
	        mService = binder.getService();
	        mBound = true;
	    }

	    // Al desconectar
	    public void onServiceDisconnected(ComponentName className) {
	        mBound = false;
	    }
	};
	
	private Messenger mMsgService;
	private boolean mMsgBound;
	
	private ServiceConnection mMsgConnection = new ServiceConnection() {
	    // LLamado al conectar el servicio
	    public void onServiceConnected(ComponentName className, IBinder service) {
	    	mMsgService = new Messenger(service);
	    	mMsgBound = true;
	    }

	    // Al desconectar
	    public void onServiceDisconnected(ComponentName className) {
	    	mMsgBound = false;
	    }
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Lanzar servicios
		lanzarServicios();
		
		this.findViewById(R.id.btnActualizar1).setOnClickListener(this);
		this.findViewById(R.id.btnActualizar2).setOnClickListener(this);

		inicializar();
	}

	/**
	 * Lanzar los servicios
	 */
	private void lanzarServicios() {
		if (!comprobarActivo(AvisoService.NAME)) {
			Intent intent = new Intent(this, AvisoService.class);

			this.startService(intent);
		}

		if (!comprobarActivo(ActualizacionService.NAME)) {
			Intent intent = new Intent(this, ActualizacionService.class);

			this.startService(intent);
		}
		
		Intent intent = new Intent(this, ActualizacionBoundService.class);
		this.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

		intent = new Intent(this, ActualizacionMsgService.class);
		this.bindService(intent, mMsgConnection, Context.BIND_AUTO_CREATE);

	}

	/**
	 * Comprobar si un servicio se encuentra activo.
	 * 
	 * @param servicio servicio a comprobar.
	 * @return true si el servicio se encuentra activo.
	 */
	private boolean comprobarActivo(String servicio) {
		boolean res = false;

		ActivityManager manager = (ActivityManager) getSystemService(Activity.ACTIVITY_SERVICE);
		for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
			if (servicio.equals(service.service.getClassName())) {
				res = true;
				break;
			}
		}

		return res;
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		//Asignar callback (ActualizacionService)
		((ServiceApp) this.getApplication()).attach(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		
		//Retirar callback (ActualizacionService)
		((ServiceApp) this.getApplication()).attach(null);
	}

	/**
	 * Inicializar interfaz (inicio de la app y callback)
	 */
	public void inicializar() {
		ContadorDao dao = DaoFactory.getContadorDao(this);

		final Contador cont = dao.buscar();

		//Ejecutar en proceso independiente
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				TextView tv = (TextView) findViewById(R.id.tvContador);
				tv.setText(Integer.toString(cont.getContador()));

			}

		});

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		case R.id.btnActualizar1:
			if (mBound){
				//Actualizar con el servicio
				mService.actualizar();
			}
			break;
		case R.id.btnActualizar2:
			if (mMsgBound){
				//Actualizar con el servicio
				try {
					mMsgService.send(new Message());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
			break;
		}
	}

}
