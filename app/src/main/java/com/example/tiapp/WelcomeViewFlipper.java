package com.example.tiapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ViewFlipper;

import static android.content.ContentValues.TAG;

public class WelcomeViewFlipper extends Activity {
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
                    if (viewFlipper.getDisplayedChild() == 1)
                        end();


                    viewFlipper.setInAnimation(this, R.anim.slide_in_from_right);
                    viewFlipper.setOutAnimation(this, R.anim.slide_out_to_left);
                    viewFlipper.showPrevious();

                    setStatusBarColor(++currentWindow);
                }
                break;
        }
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