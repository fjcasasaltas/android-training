package com.fjcasasaltas.services;

import android.app.IntentService;
import android.content.Intent;
import com.fjcasasaltas.services.callback.Callback;
import com.fjcasasaltas.services.data.DaoFactory;
import com.fjcasasaltas.services.model.Contador;
import com.fjcasasaltas.services.model.dao.ContadorDao;

public class ActualizacionService extends IntentService{
	
	public static final String NAME = "com.fjcasasaltas.service.ActualizacionService";

	public ActualizacionService(){
		super(NAME);
	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		//Simulación de espera
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//Modificar el contador
		ContadorDao dao = DaoFactory.getContadorDao(this);
		Contador cont = dao.buscar();
		cont.setContador(cont.getContador() + 1);
		
		dao.modificar(cont);
		
		//Actualización de UI: callback con application
		Callback callback = (ServiceApp) getApplication();
		callback.onFinish();
		
	}
	
	
	
	

}
