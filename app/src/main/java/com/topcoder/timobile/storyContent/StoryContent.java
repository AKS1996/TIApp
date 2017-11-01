package com.topcoder.timobile.storyContent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.topcoder.timobile.R;
import com.topcoder.timobile.browse_story.BrowseStory;
import com.github.fcannizzaro.materialstepper.AbstractStep;
import com.github.fcannizzaro.materialstepper.style.DotStepper;

public class StoryContent extends DotStepper {

    private int chapterPosition = 1;
    private int StoryPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setErrorTimeout(1500);
        setTitle("");
        setDarkPrimaryColor(R.color.white);

        if (savedInstanceState == null)
            StoryPosition = 1;
        else
            StoryPosition = savedInstanceState.getInt(getResources().getString(R.string.story_position),1);

        addStep(createFragment(new StoryContentFragment()));
        addStep(createFragment(new StoryContentFragment()));
        addStep(createFragment(new StoryContentFragment()));

        super.onCreate(savedInstanceState);


        // Can't remove toolbar
        // only add layouts as views
        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = vi.inflate(R.layout.toolbar_story_content, null);
        getToolbar().addView(v);
}

    private AbstractStep createFragment(AbstractStep fragment) {
        Bundle b = new Bundle();
        b.putInt("position", chapterPosition++);
        b.putInt(getResources().getString(R.string.story_position), StoryPosition);
        fragment.setArguments(b);
        return fragment;
    }


    // TODO Listener to add
    public void myListener(View v){
        startActivity(new Intent(this, BrowseStory.class));
    }
}
