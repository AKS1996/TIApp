package com.topcoder.timobile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText email = (EditText) findViewById(R.id.editTextEmail);
        final EditText password = (EditText) findViewById(R.id.editTextPassword);

        findViewById(R.id.sign_in_email).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().equals(getResources().getString(R.string.login_email)) && password.getText().toString().equals(getResources().getString(R.string.login_password))){
                    SharedPreferences settings = getSharedPreferences(Utils.myPrefs, 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean(Utils.newUser, false);
                    editor.apply();
                    end();
                }
                else {
                    findViewById(R.id.textErrorLogin).setVisibility(View.VISIBLE);
                    findViewById(R.id.sign_in_email).setBackgroundColor(getResources().getColor(R.color.myRed));
                }

            }
        });
    }

    private void end(){
        finish();
        startActivityForResult(new Intent(this, DotClass.class),1);
    }
}
