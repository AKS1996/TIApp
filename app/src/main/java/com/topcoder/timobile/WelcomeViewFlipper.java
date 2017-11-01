package com.topcoder.timobile;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.ViewFlipper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WelcomeViewFlipper extends Activity {
    private ViewFlipper viewFlipper;
    private float lastX;
    private int currentWindow;
    TextView textViewSmall;

    List<String> textSmall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_view_flipper);
        Utils.darkenStatusBar(this, R.color.violet);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        currentWindow = 1;

        textSmall = new ArrayList<>();
        textSmall.add("A self-guide app with fun interactions");
        textSmall.add("Play to unlock more badges and cards!");
        textSmall.add("Gather points to earn more rewards!");

        textViewSmall = (TextView) findViewById(R.id.WelcomeTextSmall);

        String textLarge;
        try {
            textLarge = (new JSONObject(Utils.loadJSONFromAsset(this, "latin.json"))).getString("data");
        }catch (JSONException j){
            textLarge = j.getMessage();
        }

        textViewSmall.setText(textSmall.get(0));
        ((TextView) findViewById(R.id.WelcomeTextLarge)).setText(textLarge);
    }

    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {

            case MotionEvent.ACTION_DOWN:
                lastX = touchevent.getX();
                break;
            case MotionEvent.ACTION_UP:
                float currentX = touchevent.getX();

                if (lastX < currentX) {

                    // If there aren't any other children, just break.
                    if (viewFlipper.getDisplayedChild() == 0)
                        end();

                    viewFlipper.setInAnimation(this, R.anim.slide_in_from_left);
                    viewFlipper.setOutAnimation(this, R.anim.slide_out_to_right);
                    viewFlipper.showNext();
                    setStatusBarColor(--currentWindow);
                }

                if (lastX > currentX) {

                    // If there is a child (to the left), just break.
                    if (viewFlipper.getDisplayedChild() == 1) {
                        end();
                    }


                    viewFlipper.setInAnimation(this, R.anim.slide_in_from_right);
                    viewFlipper.setOutAnimation(this, R.anim.slide_out_to_left);
                    viewFlipper.showPrevious();

                    setStatusBarColor(++currentWindow);
                }
                break;
        }

        textViewSmall.setText(textSmall.get(currentWindow-1));
        return false;
    }

    private void setStatusBarColor(int n){
        switch (n) {
            case 1:Utils.darkenStatusBar(this, R.color.violet);break;
            case 2:Utils.darkenStatusBar(this, R.color.green);break;
            case 3:Utils.darkenStatusBar(this, R.color.orange);break;
        }
    }

    private void end() {
        SharedPreferences settings = getSharedPreferences("prefs", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("firstRun", false);
        editor.apply();

        finish();
    }
}