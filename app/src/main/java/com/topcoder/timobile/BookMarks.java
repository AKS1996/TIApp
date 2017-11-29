package com.topcoder.timobile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.topcoder.timobile.browse_story.BrowseStory;
import com.topcoder.timobile.help.Help;
import com.topcoder.timobile.points.Points;
import com.topcoder.timobile.profile.Profile;
import com.topcoder.timobile.rewards.Rewards;
import com.topcoder.timobile.story.CardDetailsModel;
import com.topcoder.timobile.story.CustomCardAdapter;
import com.topcoder.timobile.story.Story;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BookMarks extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ListView cardslistView;
    private CustomCardAdapter customCardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_marks);

         cardslistView = (ListView) findViewById(R.id.fav_card_list_view);

        setNavBar();


    }

    @Override
    protected void onResume() {

        super.onResume();

        customCardAdapter = new CustomCardAdapter(this, R.layout.custom_card_adapter,
                new ArrayList<CardDetailsModel>());

     //   customCardAdapter.notifyDataSetChanged();
        setListItems();
        Log.d("onresume","ffef");
    }

    private void setNavBar() {
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
    }

    private void setListItems() {
        try {
            JSONObject obj = new JSONObject(Utils.loadJSONFromAsset(this, "stories.json"));
            SharedPreferences aSharedPreferences = this.getSharedPreferences(
                    "Favourite", Context.MODE_PRIVATE);
            for (int i = 1; i <= obj.length(); ++i) {
                if (aSharedPreferences.getBoolean("State" + i, false)) {
                    Log.d("id",""+i);
                    JSONObject storySelected = obj.getJSONObject(i + "");
                    String title = storySelected.getString("title");
                    String subtitle = storySelected.getString("subtitle");
                    int cards = storySelected.getInt("cards");
                    int chapters = storySelected.getJSONArray("chapters").length();
                    customCardAdapter.add(new CardDetailsModel(subtitle, title, cards, chapters, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce luctus congue mauris",i));

                }
            }
        } catch (JSONException j) {
            j.printStackTrace();
        }

        cardslistView.setAdapter(customCardAdapter);
        cardslistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(), BrowseStory.class);
                CardDetailsModel cardDetailsModel=(CardDetailsModel) cardslistView.getItemAtPosition(position);
                intent.putExtra(getResources().getString(R.string.story_position),cardDetailsModel.getId());
                startActivity(intent);
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
