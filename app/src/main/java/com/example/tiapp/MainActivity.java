package com.example.tiapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences settings = getSharedPreferences("prefs", 0);
        if ( settings.getBoolean("firstRun", true)) {
            startActivity(new Intent(MainActivity.this, WelcomeViewFlipper.class));
        }
        if ( settings.getBoolean("newUser", true)) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }

        setContentView(R.layout.activity_main);
        Utils.darkenStatusBar(this, R.color.violet);
    }

}
