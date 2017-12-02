package com.topcoder.timobile.others;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.topcoder.timobile.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Welcome extends AppCompatActivity {
    static List<String> textSmall;
    static String textLarge;
    static int total=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        textSmall = new ArrayList<>();
        textSmall.add("A self-guide app with fun interactions");
        textSmall.add("Play to unlock more badges and cards!");
        textSmall.add("Gather points to earn more rewards!");

        try {
            textLarge = (new JSONObject(Utils.loadJSONFromAsset(this, "latin.json"))).getString("data");
        }catch (JSONException j){
            textLarge = j.getMessage();
        }



        // Tab part starts
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPagerWelcome);
        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                final PlaceholderFragment placeholderFragment= new PlaceholderFragment();
                placeholderFragment.setPosition(position);
                return placeholderFragment;
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDotsWelcome);
        tabLayout.setupWithViewPager(mViewPager, true);
    }

    @Override
    public void onBackPressed() {
        end();
    }

    @Override
    protected void onStop() {
        super.onStop();
        end();
    }

    private void end() {
        SharedPreferences settings = getSharedPreferences(Utils.myPrefs, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(Utils.firstRun, false);
        editor.apply();
    }

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "position";

        static PlaceholderFragment[] fragments;

        int position;
        void setPosition(int p){
            this.position = p;
        }

        // TODO Resolve me plz
//        public PlaceholderFragment() {
//            fragments = new PlaceholderFragment[3];
//        }
//
//        public static PlaceholderFragment getInstance(int sectionNumber) {
////            PlaceholderFragment fragment = new PlaceholderFragment();
////            Toast.makeText(getBaseContext(),position+"",Toast.LENGTH_SHORT).show();
//            Bundle args = new Bundle();
//            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//            fragments[sectionNumber].setArguments(args);
//            return fragments[sectionNumber];
//        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_welcome, container, false);
            ImageView imageView = (ImageView) rootView.findViewById(R.id.imageViewWelcome);
//            int position = getArguments().getInt(ARG_SECTION_NUMBER);
            switch (position+1){
                case 1:
                    imageView.setImageResource(R.drawable.welcome1);
                    imageView.setBackgroundColor(getResources().getColor(R.color.violet));
                    Utils.darkenStatusBar(getActivity(), R.color.violet);
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.welcome2);
                    imageView.setBackgroundColor(getResources().getColor(R.color.orange));
                    Utils.darkenStatusBar(getActivity(), R.color.orange);
                    break;
                case 3:
                    imageView.setImageResource(R.drawable.welcome3);
                    imageView.setBackgroundColor(getResources().getColor(R.color.green));
                    Utils.darkenStatusBar(getActivity(), R.color.green);
                    break;
            }

            ((TextView) rootView.findViewById(R.id.WelcomeTextSmall)).setText(textSmall.get(position));
            ((TextView) rootView.findViewById(R.id.WelcomeTextLarge)).setText(textLarge);

            return rootView;
        }
    }
}