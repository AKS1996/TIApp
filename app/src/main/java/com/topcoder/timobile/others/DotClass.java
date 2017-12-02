package com.topcoder.timobile.others;

import android.os.Bundle;

import com.github.fcannizzaro.materialstepper.AbstractStep;
import com.github.fcannizzaro.materialstepper.style.DotStepper;
import com.topcoder.timobile.R;

public class DotClass extends DotStepper {

    private int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setErrorTimeout(1500);
        setTitle("Thoroughbred Insider");
        setDarkPrimaryColor(getResources().getColor(R.color.violet));
        setDarkPrimaryColor(getResources().getColor(R.color.violet));

        addStep(createFragment(new PreStory()));
        addStep(createFragment(new PreStory()));
        addStep(createFragment(new PreStory()));

        super.onCreate(savedInstanceState);
    }

    private AbstractStep createFragment(AbstractStep fragment) {
        Bundle b = new Bundle();
        b.putInt("position", i++);
        fragment.setArguments(b);
        return fragment;
    }

    /**
     * Finish this activity and revert to Story Activity
     */
    @Override
    public void onComplete(){
        finish();
    }

}
