package com.fjcasasaltas.sqlite;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.fjcasasaltas.sqlite.dao.UserDao;
import com.fjcasasaltas.sqlite.model.User;
import com.fjcasasaltas.sqlite.util.DaoFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The main activity of the application.
 *
 * @author fjcasasaltas
 * @since 1.0
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        UserDao userDao = DaoFactory.getUserDao(this);
        List<User> users = userDao.getAll();

        Spinner spinner = (Spinner) this.findViewById(R.id.usersSpinner);
        spinner.setAdapter(new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1, (ArrayList<User>) users));

    }

}
