package com.example.tiapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivityForResult(new Intent(this, DotClass.class),1);
//        SharedPreferences settings = getSharedPreferences("prefs", 0);
//        if ( settings.getBoolean("newUser", true)) {
//            startActivity(new Intent(MainActivity.this, LoginActivity.class));
//        }
//        if ( settings.getBoolean("firstRun", true)) {
//            startActivity(new Intent(MainActivity.this, WelcomeViewFlipper.class));
//        }
        setContentView(R.layout.activity_main);
    }

}
