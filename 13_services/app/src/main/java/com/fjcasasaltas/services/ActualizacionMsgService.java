package com.fjcasasaltas.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import com.fjcasasaltas.services.data.DaoFactory;
import com.fjcasasaltas.services.model.Contador;
import com.fjcasasaltas.services.model.dao.ContadorDao;

public class ActualizacionMsgService extends Service{
	
	public static final String NAME = "com.fjcasasaltas.service.ActualizacionMsgService";
	
	private Messenger mMessenger = new Messenger(new IncomingHandler());
	
	public class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
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
					com.fjcasasaltas.services.callback.Callback callback = (ServiceApp) getApplication();
    				callback.onFinish();
    			}
    		}).start();
        }
    }
	
	@Override
	public IBinder onBind(Intent intent) {
		return mMessenger.getBinder();
	}
	
	
}
