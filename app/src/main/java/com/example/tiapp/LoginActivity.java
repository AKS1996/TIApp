package com.example.tiapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
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
                if (email.getText().toString().equals("hello") && password.getText().toString().equals("world")){
                    SharedPreferences settings = getSharedPreferences("prefs", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean("newUser", false);
                    editor.apply();

                    end();
                }
                else
                    Toast.makeText(LoginActivity.this,"Invalid Id password",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void end(){
        finish();
        startActivityForResult(new Intent(this, DotClass.class),1);
    }
}
