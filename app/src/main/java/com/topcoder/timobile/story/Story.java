package com.topcoder.timobile.story;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.topcoder.timobile.R;
import com.topcoder.timobile.browse_story.BrowseStory;
import com.topcoder.timobile.help.Help;
import com.topcoder.timobile.others.DotClass;
import com.topcoder.timobile.others.LoginActivity;
import com.topcoder.timobile.others.Utils;
import com.topcoder.timobile.others.WelcomeViewFlipper;
import com.topcoder.timobile.points.Points;
import com.topcoder.timobile.profile.Profile;
import com.topcoder.timobile.rewards.Rewards;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * This is the Main Activity
 */
public class Story extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarStory);
        setSupportActionBar(myToolbar);

        SharedPreferences settings = getSharedPreferences(Utils.myPrefs, 0);
        if ( settings.getBoolean(Utils.newUser, true)) {
            startActivity(new Intent(this, LoginActivity.class));
        }
        if (settings.getBoolean(Utils.firstRun, true)) {
            startActivity(new Intent(this, WelcomeViewFlipper.class));
            startActivity(new Intent(this, DotClass.class));
        }


        /*
         * CardView and Adapter Starts
         */
        final List<CardDetailsModel> cardDetailsModelList = new ArrayList<>();
        final ListView cardslistView = (ListView) findViewById(R.id.card_list_view);
        final CustomCardAdapter customCardAdapter = new CustomCardAdapter(this, R.layout.custom_card_adapter, cardDetailsModelList);
        loadContentsIntoCards(customCardAdapter);

        cardslistView.setAdapter(customCardAdapter);
        cardslistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CardDetailsModel cardDetailsModel = (CardDetailsModel) cardslistView.getItemAtPosition(position);
                Intent intent = new Intent(getBaseContext(), BrowseStory.class);
                intent.putExtra(getResources().getString(R.string.story_position), cardDetailsModel.getId());
                startActivity(intent);
            }
        });

        /*
         * CardView Ends
         * Setting Adapters
         */
        findViewById(R.id.MapButtonStory).setOnClickListener(this);
        findViewById(R.id.SearchButtonStory).setOnClickListener(this);


        /*
         * Button Adapters end
         * Populate Spinners Starts
         * First is dynamic, read from JSON. Second is static.
         * That '3' is based on JSON file
         */
        List<String> spinnerArray1 = loadContents(Story.this);
        List<String> spinnerArray2 = new ArrayList<>();
        spinnerArray1.add(0, "Select a racetrack");
        spinnerArray2.add(0, "Select sort criteria");

        // Spinner 2 is static - based on sorting choices 
        spinnerArray2.add("Cards Ascending");
        spinnerArray2.add("Cards Descending");
        spinnerArray2.add("Chapters Ascending");
        spinnerArray2.add("Chapters Descending");

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, R.layout.pre_story_row, spinnerArray1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, R.layout.pre_story_row, spinnerArray2);
        adapter1.setDropDownViewResource(R.layout.pre_story_row);
        adapter2.setDropDownViewResource(R.layout.pre_story_row);
        Spinner spinner1 = (Spinner) findViewById(R.id.Spinner1Story);
        Spinner spinner2 = (Spinner) findViewById(R.id.Spinner2Story);
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getItemAtPosition(position).toString()) {
                    case "Cards Ascending":
                        Collections.sort(cardDetailsModelList, new CardCountComparator());
                        break;
                    case "Cards Descending":
                        Collections.sort(cardDetailsModelList, new CardCountComparator());
                        Collections.reverse(cardDetailsModelList);
                        break;
                    case "Chapters Ascending":
                        Collections.sort(cardDetailsModelList, new ChapterCountComparator());
                        break;
                    case "Chapters Descending":
                        Collections.sort(cardDetailsModelList, new ChapterCountComparator());
                        Collections.reverse(cardDetailsModelList);
                        break;
                }

                // Don't forget to notify adapter
                customCardAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        /*
         * Spinners End
         * Nav Part Starts
         */
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_story);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.MapButtonStory:
                startActivity(new Intent(Story.this, MapsActivity.class));
                break;
            case R.id.SearchButtonStory:
                startActivity(new Intent(Story.this, Search.class));
                break;
        }

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_story_selection:
                startActivity(new Intent(this, Story.class));
                break;
            case R.id.nav_bookmarks:
                startActivity(new Intent(this, BookMarks.class));
                break;
            case R.id.nav_help:
                startActivity(new Intent(this, Help.class));
                break;
            case R.id.nav_logout:
                Utils.logout(getBaseContext());
                break;
            case R.id.nav_rewards:
                startActivity(new Intent(this, Rewards.class));
                break;
            case R.id.nav_profile:
                startActivity(new Intent(this, Profile.class));
                break;
            case R.id.nav_points:
                startActivity(new Intent(this, Points.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_story);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private List<String> loadContents(Context context) {
        List<String> listContents = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(Utils.loadJSONFromAsset(context, "prestory.json"));
            JSONArray jsonArray = jsonObject.getJSONArray("2");
            for (int i = 0; i < jsonArray.length(); i++)
                listContents.add(jsonArray.getString(i));
        } catch (JSONException j) {
            Log.e(Utils.TAG, j.getMessage());
        }

        return listContents;
    }

    private void loadContentsIntoCards(CustomCardAdapter customCardAdapter) {
        try {
            JSONObject obj = new JSONObject(Utils.loadJSONFromAsset(this, "stories.json"));
            for (int i = 1; i <= obj.length(); ++i) {
                JSONObject storySelected = obj.getJSONObject(i + "");
                String title = storySelected.getString("title");
                String subtitle = storySelected.getString("subtitle");
                int cards = storySelected.getInt("cards");
                int chapters = storySelected.getJSONArray("chapters").length();
                customCardAdapter.add(new CardDetailsModel(subtitle, title, cards, chapters,
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce luctus congue mauris", i));
            }
        } catch (JSONException j) {
            j.printStackTrace();
        }
    }

    public class CardCountComparator implements Comparator<CardDetailsModel> {
        @Override
        public int compare(CardDetailsModel o1, CardDetailsModel o2) {
            return o1.getCardCount() - o2.getCardCount();
        }
    }

    public class ChapterCountComparator implements Comparator<CardDetailsModel> {
        @Override
        public int compare(CardDetailsModel o1, CardDetailsModel o2) {
            return o1.getChapterCount() - o2.getChapterCount();
        }
    }
}
