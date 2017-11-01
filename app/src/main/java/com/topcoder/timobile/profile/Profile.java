package com.topcoder.timobile.profile;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TableLayout;

import com.topcoder.timobile.R;
import com.topcoder.timobile.Settings;
import com.topcoder.timobile.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    // TODO add badges icons
    // TODO Read data from json for every activity
    // TODO twitch graphics for all activities

    boolean fourBoxTableGone = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarProfile);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");

        findViewById(R.id.dropdownProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fourBoxTableGone) {
                    findViewById(R.id.fourBoxTableProfile).setVisibility(TableLayout.VISIBLE);
                    fourBoxTableGone = false;
                }else{
                    findViewById(R.id.fourBoxTableProfile).setVisibility(TableLayout.GONE);
                    fourBoxTableGone = true;
                }
            }
        });

        findViewById(R.id.settingsIconProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(), Settings.class));
            }
        });


        // Tab part starts
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewPagerProfile);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabsProfile);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));


        // Nav part starts
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_profile);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, myToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerview = navigationView.getHeaderView(0);
        headerview.findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.START))
                    drawer.closeDrawer(GravityCompat.START);
            }
        });
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_story_selection:
                break;
            case R.id.nav_bookmarks:
                break;
            case R.id.nav_help:
                break;
            case R.id.nav_logout:
                break;
            case R.id.nav_rewards:
                break;
            case R.id.nav_profile:
                break;
            case R.id.nav_points:
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_profile);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }
    }

    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number_profile_acitivity";

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        // TODO improv; store shit in DB, dont read again
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
            GridView gridView = (GridView) rootView.findViewById(R.id.gridViewProfile);
            gridView.setAdapter(new GridViewAdapter(getContext(),getArguments().getInt(ARG_SECTION_NUMBER)));

            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {

                    try {
                        JSONObject obj = new JSONObject(Utils.loadJSONFromAsset(getContext(), "profile.json"));
                        JSONArray selectedArray = obj.getJSONArray(getArguments().getInt(ARG_SECTION_NUMBER)+"");
                        JSONObject content = selectedArray.getJSONObject(position);
                        (new CustomDialogClass(getActivity(),content.getString("name"),content.getString("description"))).show();
                    }catch(JSONException j){
                        j.printStackTrace();
                    }
                }
            });

            return rootView;
        }
    }

}
