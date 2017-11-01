package com.example.tiapp;


import android.os.Bundle;

import com.github.fcannizzaro.materialstepper.AbstractStep;
import com.github.fcannizzaro.materialstepper.style.DotStepper;

public class DotClass extends DotStepper {
    // TODO add material stepper colors

    private int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setErrorTimeout(1500);
        setTitle("Thoroughbred Insider");
        setDarkPrimaryColor(R.color.white);

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

}
