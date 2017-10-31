package com.example.tiapp.profile;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.tiapp.R;

public class CustomDialogClass extends Dialog {

    public Activity c;
    private String name, description;
    public Dialog d;

    public CustomDialogClass(Activity a, String Name, String Description) {
        super(a);
        this.c = a;
        name = Name;
        description = Description;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.custom_dialog);
        ((TextView) findViewById(R.id.profileDialogName)).setText(name);
        ((TextView) findViewById(R.id.profileDialogDescrip)).setText(description);
        findViewById(R.id.profileDialogButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


    }
}
