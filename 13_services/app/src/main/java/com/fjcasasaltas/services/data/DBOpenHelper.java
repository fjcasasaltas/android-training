package com.fjcasasaltas.services.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "testdb";

	private static DBOpenHelper dbOpen;

	private DBOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		dbOpen = this;
	}

	public static DBOpenHelper getInstance(Context context) {
		if (dbOpen == null) {
			new DBOpenHelper(context);
		}
		return dbOpen;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try{
			//Crear las tablas
			db.execSQL("CREATE TABLE contador(" +						
						"id integer PRIMARY KEY," +
						"contador integer NOT NULL);");
			
			db.execSQL("INSERT INTO contador(id, contador) VALUES (1, 0);");
		}catch (Exception ex){
			;
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
