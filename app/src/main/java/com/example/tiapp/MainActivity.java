package com.example.tiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tiapp.help.Help;
import com.example.tiapp.profile.Profile;

public class MainActivity extends AppCompatActivity {

    // TODO reduce styles.xml waste AppThemes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        startActivity(new Intent(this, Profile.class));
//        startActivity(new Intent(this, Settings.class));
        startActivity(new Intent(this, Profile.class));
//        startActivityForResult(new Intent(this, DotClass.class),1);
//        SharedPreferences settings = getSharedPreferences("prefs", 0);
//        if ( settings.getBoolean("newUser", true)) {
//            startActivity(new Intent(Main.this, LoginActivity.class));
//        }
//        if ( settings.getBoolean("firstRun", true)) {
//            startActivity(new Intent(Main.this, WelcomeViewFlipper.class));
//        }
    }
}
