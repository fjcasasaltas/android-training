package com.fjcasasaltas.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootUpReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent arg) {
		//Lanzar los servicios al arrancar el sistema
		Intent intent = new Intent(context, AvisoService.class);
		context.startService(intent);

		intent = new Intent(context, ActualizacionService.class);
		context.startService(intent);
		
		intent = new Intent(context, ActualizacionBoundService.class);
		context.startService(intent);
		
		intent = new Intent(context, ActualizacionMsgService.class);
		context.startService(intent);
	}

}
