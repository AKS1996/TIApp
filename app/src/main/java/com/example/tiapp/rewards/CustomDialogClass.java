package com.example.tiapp.rewards;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.tiapp.R;

public class CustomDialogClass extends Dialog {
    private String name, description;
    private int money;
    public Dialog d;

    public CustomDialogClass(Context a, int m, String Name, String Description) {
        super(a);
        money = m;
        name = Name;
        description = Description;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.custom_dialog_rewards);
        ((TextView) findViewById(R.id.rewardsDialogName)).setText(name);
        ((TextView) findViewById(R.id.rewardsDailogMoney)).setText(money+" pts");
        ((TextView) findViewById(R.id.rewardsDialogDescrip)).setText(description);
        findViewById(R.id.rewardsDialogButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


    }
}
