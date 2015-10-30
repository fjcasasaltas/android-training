package com.fjcasasaltas.sqlite.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * TODO Description.
 *
 * @author fjcasasaltas
 * @since 1.0
 */
public class AppSQLiteOpenHelper extends SQLiteOpenHelper {

    public AppSQLiteOpenHelper(Context context, String databaseName, int databaseVersion) {
        super(context, databaseName, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE user (id INTEGER PRIMARY KEY, username NVARCHAR(15) NOT NULL, pin SMALLINT NOT NULL);");

        db.execSQL("INSERT INTO user (username, pin) VALUES ('admin', 1234);");
        db.execSQL("INSERT INTO user (username, pin) VALUES ('user1', 1111);");
        db.execSQL("INSERT INTO user (username, pin) VALUES ('user2', 2222);");
        db.execSQL("INSERT INTO user (username, pin) VALUES ('user3', 3333);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
