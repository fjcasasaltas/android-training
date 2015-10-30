package com.fjcasasaltas.services.model.dao.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fjcasasaltas.services.data.DBOpenHelper;
import com.fjcasasaltas.services.model.Contador;
import com.fjcasasaltas.services.model.dao.ContadorDao;

public class ContadorDaoImpl implements ContadorDao {

	private static final String TABLA = "Contador";
	
	private DBOpenHelper dbOpen;
	
	public ContadorDaoImpl(Context context){
		this.dbOpen = DBOpenHelper.getInstance(context);		
	}

	@Override
	public Contador buscar() {
		Contador res = null;

		Cursor c = null;
		SQLiteDatabase db = null;

		try {
			// Obtener acceso de solo lectura
			db = dbOpen.getReadableDatabase();
			String[] columnas = { "id", "contador" };
			c = db.query(TABLA, columnas, null, null, null, null, null);
			
			if (c.moveToNext()) {
				res = new Contador();
				res.setId(c.getInt(0));
				res.setContador(c.getInt(1));
			}

		} finally {
			if (c != null) {
				c.close();
			}
			if (db != null) {
				db.close();
			}
		}

		return res;
	}
	
	@Override
	public void modificar(Contador contador){
		ContentValues values = new ContentValues();
		values.put(Contador.FIELD_CONT, contador.getContador());
		
		SQLiteDatabase db = null;
		
		try{
			db = dbOpen.getWritableDatabase();
			db.update(TABLA, values, Contador.FIELD_ID + "=" + contador.getId(), null);
		} finally {
			if (db != null) {
				db.close();
			}
		}
	}

}
