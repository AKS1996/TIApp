package com.example.tiapp.browse_story;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.tiapp.R;

public class BrowseStory extends AppCompatActivity implements View.OnClickListener{
    private ImageButton mFavouriteButton,mBackButton;
    private Button mTabSwitch,mCameraButton;
    private LinearLayout threeElementLayout;
    private Boolean tabsBoolean ;
    private LinearLayout storyProgress;
    private Boolean viewstate=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_story);

        storyProgress=(LinearLayout) findViewById(R.id.progressBarLayout);
        threeElementLayout =(LinearLayout) findViewById(R.id.threeElementLayout);
        storyProgress.setVisibility(View.INVISIBLE);

        mTabSwitch=(Button)findViewById(R.id.tabSwitch);
        mBackButton=(ImageButton)findViewById(R.id.back_button);
        mFavouriteButton=(ImageButton)findViewById(R.id.favorite_button);
        mCameraButton=(Button)findViewById(R.id.cameraintent);
        mTabSwitch.setOnClickListener(this);
        setPreviousStateForFav();
        mFavouriteButton.setOnClickListener(this);
        mBackButton.setOnClickListener(this);
        mCameraButton.setOnClickListener(this);
    }

    private void setPreviousStateForFav() {
        boolean isFavourite = readState();
        if (isFavourite) {
            mFavouriteButton.setImageResource(R.drawable.ic_star_black_24dp);
        } else {
            mFavouriteButton.setImageResource(R.drawable.ic_star_border_black_24dp);
        }
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

        boolean isFavourite = readState();

        if (isFavourite) {
            mFavouriteButton.setImageResource(R.drawable.ic_star_border_black_24dp);
            isFavourite = false;
            saveState(isFavourite);

        } else {

            mFavouriteButton.setImageResource(R.drawable.ic_star_black_24dp);
            isFavourite = true;
            saveState(isFavourite);

        }


    }

    private void saveState(boolean isFavourite) {
        SharedPreferences aSharedPreferences = this.getSharedPreferences(
                "Favourite", Context.MODE_PRIVATE);
        SharedPreferences.Editor aSharedPreferencesEdit = aSharedPreferences
                .edit();
        aSharedPreferencesEdit.putBoolean("State", isFavourite);
        aSharedPreferencesEdit.apply();
    }

    private boolean readState() {
        SharedPreferences aSharedPreferences = this.getSharedPreferences(
                "Favourite", Context.MODE_PRIVATE);
        return aSharedPreferences.getBoolean("State", true);
    }

}

