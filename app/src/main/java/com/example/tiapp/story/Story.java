package com.example.tiapp.story;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tiapp.R;
import com.example.tiapp.browse_story.BrowseStory;

import java.util.ArrayList;

public class Story extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarStory);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");


	    // comments and card adapters
        ListView cardslistView=(ListView)findViewById(R.id.card_list_view);
        CustomCardAdapter  customCardAdapter=new  CustomCardAdapter(this,R.layout.custom_card_adapter,
                new ArrayList<CardDetailsModel>());
        customCardAdapter.add(new CardDetailsModel("fhs","dhgfdhg","hgfgsdggd"));
        customCardAdapter.add(new CardDetailsModel("fhs","dhgfdhg","hgfgsdggd"));
        customCardAdapter.add(new CardDetailsModel("fhs","dhgfdhg","hgfgsdggd"));

        cardslistView.setAdapter(customCardAdapter);
        cardslistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getBaseContext(),BrowseStory.class);
                intent.putExtra(getResources().getString(R.string.story_position),position);
                startActivity(intent);
            }
        });


        // Nav part starts
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_story);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
