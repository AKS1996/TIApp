package com.topcoder.timobile.others;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.topcoder.timobile.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class WelcomeViewFlipper extends Activity{
    TextView textViewSmall;
    List<String> textSmall;
    private ViewFlipper viewFlipper;
    private float lastX;
    private int currentWindow;

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

        if (currentWindow<=3 && currentWindow >= 1)
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

    /**
     * TextView Click Listener associated with "Skip this!"
     * in activity Welcome. Ends current activity, sets firstUser as false
     */
    public void SkipActionClickListener(View view) {
        startActivity(new Intent(getBaseContext(),DotClass.class));
        end();
    }

    private void end() {
        SharedPreferences settings = getSharedPreferences(Utils.myPrefs, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(Utils.firstRun, false);
        editor.apply();
        finish();
    }
}