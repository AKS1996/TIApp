package com.topcoder.timobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.topcoder.timobile.browse_story.BrowseStory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(this, BrowseStory.class));
//        startActivity(new Intent(this, Help.class));

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
