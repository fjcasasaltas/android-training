package com.fjcasasaltas.sqlite.dao.impl;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.fjcasasaltas.sqlite.util.AppSQLiteOpenHelper;

/**
 * TODO Description.
 *
 * @author fjcasasaltas
 * @since 1.0
 */
public abstract class AbstractSQLiteDAO {

    private static final String DATABASE_NAME = "testdb";

    private static final int DATABASE_VERSION = 1;

    private static SQLiteOpenHelper dbOpenHelper;

    protected AbstractSQLiteDAO(Context context) {
        if (dbOpenHelper == null) {
            dbOpenHelper = new AppSQLiteOpenHelper(context, DATABASE_NAME, DATABASE_VERSION);
        }
    }

    public SQLiteOpenHelper getSQLiteOpenHelper()
    {
        return dbOpenHelper;
    }

}
