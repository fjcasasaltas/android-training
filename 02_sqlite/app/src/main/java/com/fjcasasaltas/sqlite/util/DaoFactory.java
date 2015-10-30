package com.fjcasasaltas.sqlite.util;


import android.content.Context;

import com.fjcasasaltas.sqlite.dao.UserDao;
import com.fjcasasaltas.sqlite.dao.impl.UserDaoImpl;

/**
 * TODO Description.
 *
 * @author fjcasasaltas
 * @since 1.0
 */
public abstract class DaoFactory {

    private static UserDao userDao;

    public static UserDao getUserDao(Context context){
        if (userDao == null){
            userDao = new UserDaoImpl(context);
        }
        return userDao;
    }

}
