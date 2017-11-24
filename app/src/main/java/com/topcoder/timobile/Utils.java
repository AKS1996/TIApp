package com.topcoder.timobile;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class Utils {
    public static String myPrefs = "preferences";
    public static String newUser = "new_user";
    public static String firstRun = "first_run";

    // The public static function which can be called from other classes
    public static void darkenStatusBar(Activity activity, int color) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            activity.getWindow().setStatusBarColor(
                    darkenColor(
                            ContextCompat.getColor(activity, color)));
        }

    }

    public static void logout(Context context){
        Toast.makeText(context,"Logged Out",Toast.LENGTH_SHORT).show();
        SharedPreferences settings = context.getSharedPreferences(Utils.myPrefs, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(Utils.newUser, true);
        editor.apply();
    }

    // Code to darken the color supplied (mostly color of toolbar)
    private static int darkenColor(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
//        hsv[2] *= 0.8f; // Darkens status bar a bit
        return Color.HSVToColor(hsv);
    }

    static public String loadJSONFromAsset(Context context,String fileName) {
        String json;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }


}