package com.fjcasasaltas.services;

import com.fjcasasaltas.services.callback.Callback;
import android.app.Application;

public class ServiceApp extends Application implements Callback{
	
	private MainActivity main;
	
	public void attach(MainActivity main){
		this.main = main;
	}

	@Override
	public void onFinish() {
		if (main != null){
			main.inicializar();
		}
	}

}
