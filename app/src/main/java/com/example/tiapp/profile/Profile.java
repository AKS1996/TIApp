package com.example.tiapp.profile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TableLayout;

import com.example.tiapp.R;
import com.example.tiapp.Settings;

public class Profile extends AppCompatActivity {
    // TODO add badges icons
    // TODO Read data from json for every activity
    // TODO twitch graphics for all activities

    boolean fourBoxTableGone = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarProfile);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");

        findViewById(R.id.dropdownProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fourBoxTableGone) {
                    findViewById(R.id.fourBoxTableProfile).setVisibility(TableLayout.VISIBLE);
                    fourBoxTableGone = false;
                }else{
                    findViewById(R.id.fourBoxTableProfile).setVisibility(TableLayout.GONE);
                    fourBoxTableGone = true;
                }
            }
        });

        findViewById(R.id.settingsIconProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(), Settings.class));
            }
        });
    }
}
