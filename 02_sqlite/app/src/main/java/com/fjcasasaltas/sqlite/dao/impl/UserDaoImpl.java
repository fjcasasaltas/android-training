package com.fjcasasaltas.sqlite.dao.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fjcasasaltas.sqlite.dao.UserDao;
import com.fjcasasaltas.sqlite.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * TODO Description.
 *
 * @author fjcasasaltas
 * @since 1.0
 */
public class UserDaoImpl extends AbstractSQLiteDAO implements UserDao {

    private SQLiteOpenHelper sqLiteOpenHelper;

    public UserDaoImpl(Context context) {
        super(context);
    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        Cursor cursor = null;
        SQLiteDatabase database = null;
        try {
            database = this.getSQLiteOpenHelper().getReadableDatabase();
            cursor = database.query("user", new String[] { "id", "username", "pin" }, null, null, null, null, null);
            while (cursor.moveToNext()) {
                User user = new User();
                user.setId(cursor.getInt(0));
                user.setUsername(cursor.getString(1));
                user.setPin(cursor.getShort(2));
                result.add(user);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (database != null) {
                database.close();
            }
        }
        return result;
    }

}
