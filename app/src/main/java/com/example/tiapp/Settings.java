package com.example.tiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.tiapp.profile.Profile;

public class Settings extends AppCompatActivity {

    // Implement all functions
    // Atleast the left arrow to move back
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarSettings);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");

        findViewById(R.id.backButtonSettings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(), Profile.class));
                finish();
            }
        });
    }
}
