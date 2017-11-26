package com.topcoder.timobile.rewards;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.topcoder.timobile.BookMarks;
import com.topcoder.timobile.R;
import com.topcoder.timobile.Utils;
import com.topcoder.timobile.help.Help;
import com.topcoder.timobile.points.Points;
import com.topcoder.timobile.profile.Profile;
import com.topcoder.timobile.story.Story;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Rewards extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    // TODO Goto card shop button
    // TODO Use dividers in profile activity, remove custom border
    // TODO Improve UI
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarRewards);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        // Nav part starts
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_rewards);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
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


        final GridView gridView = (GridView) findViewById(R.id.gridViewRewards);
        gridView.setAdapter(new GridViewAdapter(this));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                try {
                    JSONObject obj = new JSONObject(Utils.loadJSONFromAsset(getApplicationContext(), "rewards.json"));
                    JSONArray selectedArray = obj.getJSONArray("content");
                    JSONObject j = selectedArray.getJSONObject(position);

                    (new CustomDialogClass(parent.getContext(),j.getInt("money"),j.getString("name"),j.getString("description"))).show();
                }catch(JSONException j){
                    j.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_rewards);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_story_selection:startActivity(new Intent(this, Story.class));
                break;
            case R.id.nav_bookmarks:
                startActivity(new Intent(this, BookMarks.class));
                break;
            case R.id.nav_help:startActivity(new Intent(this, Help.class));
                break;
            case R.id.nav_logout:Utils.logout(getBaseContext());
                break;
            case R.id.nav_rewards:startActivity(new Intent(this, Rewards.class));
                break;
            case R.id.nav_profile:startActivity(new Intent(this, Profile.class));
                break;
            case R.id.nav_points:startActivity(new Intent(this, Points.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_rewards);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
