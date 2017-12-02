package com.topcoder.timobile.browse_story;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.topcoder.timobile.R;
import com.topcoder.timobile.others.Utils;
import com.topcoder.timobile.storyContent.StoryContent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BrowseStory extends AppCompatActivity implements View.OnClickListener{
    private ImageButton mFavouriteButton;
    private LinearLayout threeElementLayout;
    private LinearLayout storyProgress;
    private Boolean viewstate=false;
    private boolean isFavourite = false;

    private  int currentItemPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_story);

        Intent intent=getIntent();
        currentItemPos=intent.getIntExtra(getResources().getString(R.string.story_position),0);

        /*
         *  Populating Header Image
         *  Reading from JSON Data
         */
        JSONObject jsonObject;
        JSONArray chapterArray;
        List<String> chapterList = new ArrayList<>();
        try {
            jsonObject = (new JSONObject(Utils.loadJSONFromAsset(this, "stories.json"))).getJSONObject(currentItemPos+"");
            ((TextView) findViewById(R.id.titleTextBrowseStory)).setText(jsonObject.getString("title").concat("\n").concat(jsonObject.getString("subtitle")));
            ((TextView) findViewById(R.id.CardInfoBrowseStory)).setText(String.format(Integer.toString(jsonObject.getInt("cards")), Locale.getDefault()).concat(" Cards"));
            chapterArray = jsonObject.getJSONArray("chapters");
            ((TextView) findViewById(R.id.ChapterInfoBrowseStory)).setText(String.format(Integer.toString(chapterArray.length()), Locale.getDefault()).concat(" Chapters"));

            // Turning chapter from JSON to String
            for(int i=0;i<chapterArray.length();++i){
                JSONObject j = chapterArray.getJSONObject(i);
                chapterList.add(j.getString("titleChapter"));
            }
        }catch (JSONException j){
            j.printStackTrace();
        }

        storyProgress=(LinearLayout) findViewById(R.id.progressBarLayout);
        threeElementLayout =(LinearLayout) findViewById(R.id.threeElementLayout);

        /*
         *  Populating storyProgress Layout
         *  With Chapter Progress data
         */
        ListView listView = (ListView) findViewById(R.id.ListChaptersBrowseStory);
        CustomChapterListAdapter adapter = new CustomChapterListAdapter(this, R.layout.custom_chapter_list_view, chapterList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BrowseStory.this, StoryContent.class);
                intent.putExtra(getResources().getString(R.string.story_position),position+1);
                startActivity(intent);
                finish();
            }
        });

        /*
         *  Button Setters
         */
        mFavouriteButton=(ImageButton)findViewById(R.id.favorite_button);
        mFavouriteButton.setOnClickListener(this);
        findViewById(R.id.tabSwitch).setOnClickListener(this);
        findViewById(R.id.back_button).setOnClickListener(this);
        findViewById(R.id.cameraintent).setOnClickListener(this);
        findViewById(R.id.cameraintent).setEnabled(readRewardState());

        isFavourite = readState();
        if (isFavourite)
            mFavouriteButton.setImageResource(R.drawable.ic_star_black_24dp);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_button:
                onBackPressed();
                break;
            case R.id.favorite_button:
                favButtonClicked();
                break;
            case R.id.tabSwitch:
                switchViews();
                break;
            case R.id.cameraintent:
                startCamera();
                break;
            default:
                break;
        }
    }

    private void switchViews() {
        if(viewstate){
            storyProgress.setVisibility(View.GONE);
            threeElementLayout.setVisibility(View.VISIBLE);
            viewstate=false;
        }else{
            storyProgress.setVisibility(View.VISIBLE);
            threeElementLayout.setVisibility(View.GONE);
            viewstate=true;
        }

    }

    private void startCamera() {
        Intent intent = new Intent(getBaseContext(),CameraActivity.class);
        startActivity(intent);
    }


    private void favButtonClicked() {
        if (isFavourite) {
            mFavouriteButton.setImageResource(R.drawable.ic_star_border_black_24dp);
            isFavourite = false;
        } else {
            mFavouriteButton.setImageResource(R.drawable.ic_star_black_24dp);
            isFavourite = true;
        }
        Utils.setPreferences(getBaseContext(),"State"+currentItemPos,isFavourite);
    }

    private boolean readState() {
        SharedPreferences aSharedPreferences = this.getSharedPreferences(
                "Favourite", Context.MODE_PRIVATE);
        return aSharedPreferences.getBoolean("State"+currentItemPos, false);
    }

    private boolean readRewardState() {
        SharedPreferences aSharedPreferences = this.getSharedPreferences(
                "Reward", Context.MODE_PRIVATE);
        return aSharedPreferences.getBoolean("AllowRewards", false);
    }
}

