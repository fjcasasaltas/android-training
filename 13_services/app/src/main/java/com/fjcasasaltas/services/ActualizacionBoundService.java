package com.fjcasasaltas.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.fjcasasaltas.services.callback.Callback;
import com.fjcasasaltas.services.data.DaoFactory;
import com.fjcasasaltas.services.model.Contador;
import com.fjcasasaltas.services.model.dao.ContadorDao;

public class ActualizacionBoundService extends Service{
	
	public static final String NAME = "com.fjcasasaltas.services.ActualizacionBoundService";
	
	private ActualizacionBinder mBinder = new ActualizacionBinder();
	
	/**
     * Binder para el cliente
     */
    public class ActualizacionBinder extends Binder {
    	ActualizacionBoundService getService() {
            // Devolver instancia actual
            return ActualizacionBoundService.this;
        }
    }

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}
	
	public void actualizar() {
		new Thread(new Runnable(){

			@Override
			public void run() {
				//Simulación de espera
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
						
				//Modificar el contador
				ContadorDao dao = DaoFactory.getContadorDao(getApplicationContext());
				Contador cont = dao.buscar();
				cont.setContador(cont.getContador() + 1);
						
				dao.modificar(cont);
				
				//Actualización de UI: callback con application
				Callback callback = (ServiceApp) getApplication();
				callback.onFinish();
			}
		}).start();
	}
	
	
}
