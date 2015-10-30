package com.fjcasasaltas.services.data;

import android.content.Context;
import com.fjcasasaltas.services.model.dao.ContadorDao;
import com.fjcasasaltas.services.model.dao.impl.ContadorDaoImpl;

public class DaoFactory {
	
	private static ContadorDao contadorDao;	
	
	public static ContadorDao getContadorDao(Context context){
		if (contadorDao == null){
			contadorDao = new ContadorDaoImpl(context);
		}
		return contadorDao;
	}

}
