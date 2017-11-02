package com.topcoder.timobile;

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
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Welcome extends AppCompatActivity {
    static List<String> textSmall;
    static String textLarge;

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
                return PlaceholderFragment.newInstance(position);
            }

            @Override
            public int getCount() {
                return 3;
            }
        });
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDotsWelcome);
        tabLayout.setupWithViewPager(mViewPager, true);
    }

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "position";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_welcome, container, false);
            ImageView imageView = (ImageView) rootView.findViewById(R.id.imageViewWelcome);
            int position = getArguments().getInt(ARG_SECTION_NUMBER);
            switch (position){
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

            Toast.makeText(getContext(),position+"",Toast.LENGTH_SHORT).show();
            ((TextView) rootView.findViewById(R.id.WelcomeTextSmall)).setText(textSmall.get(position));
            ((TextView) rootView.findViewById(R.id.WelcomeTextLarge)).setText(textLarge);

            return rootView;
        }
    }

    @Override
    public void onBackPressed(){
        end();
    }

    @Override
    protected void onStop() {
        super.onStop();
        end();
    }

    private void end(){
        SharedPreferences settings = getSharedPreferences("prefs", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("firstRun", false);
        editor.apply();
    }
}