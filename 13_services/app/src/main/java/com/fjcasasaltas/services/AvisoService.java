package com.fjcasasaltas.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class AvisoService extends Service{
	
	public static final String NAME = "com.fjcasasaltas.services.AvisoService";
	
	//Modificar tiempo para notificar cada hora (ms)
	private static final int SLEEP = 60000;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		//Lanzar proceso independiente
		new Thread(new Runnable(){

			@Override
			public void run() {
				int id = 0;
				while (true){
					try {
						//Espera
						Thread.sleep(SLEEP);
						
						notificarBarra(getApplication(), id++);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
			
		}).start();
		
		return Service.START_NOT_STICKY;
	}
	
	/**
	 * Realizar notificación en barra de tareas
	 * @param context
	 * @param id
	 */
	private void notificarBarra(Context context, int id) {
		NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
	
		int icon = R.mipmap.ic_launcher;
		
		CharSequence contentTitle = context.getString(R.string.app_name);
		CharSequence contentText = context.getString(R.string.mensaje_notificacion);
	
		//Parámetros de la notificación
		Notification.Builder builder = new Notification.Builder(this)
				 .setContentTitle(contentTitle)
		         .setContentText(contentText)
		         .setSmallIcon(icon)
		         .setAutoCancel(true);
		//Configuración de alarmas (sonido, luz) por defecto
		builder.setDefaults(Notification.DEFAULT_ALL);
	
		//Encargado de lanzar la actividad principal al mostrar la alerta
		Intent notificationIntent = new Intent(context, MainActivity.class);
		notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
	
		PendingIntent contentIntent = PendingIntent.getActivity(context, id, notificationIntent, 0);
	
		builder.setContentIntent(contentIntent);
	
		//Notificar
		mNotificationManager.notify(id, builder.build());
	}

}
