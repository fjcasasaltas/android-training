package com.fjcasasaltas.helloworld;

import android.app.Activity;
import android.os.Bundle;

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
    }

}
