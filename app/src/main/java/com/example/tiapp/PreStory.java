package com.example.tiapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;

import com.github.fcannizzaro.materialstepper.AbstractStep;

public class PreStory extends AbstractStep {

    private int i = 1;
    private String[] titles = new String[3];

    // TODO Get JSON data drom API's
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        titles[0] = "1. SET UP YOUR LOCATION";
        titles[1] = "2. SELECT THE STATES";
        titles[2] = "3. SELECT THE RACETRACKS";
        Utils.darkenStatusBar(getActivity(),R.color.violet);

        View v;
        if (getArguments().getInt("position", 0) == 1) {
            v = inflater.inflate(R.layout.pre_story1, container, false);
            ((Toolbar) v.findViewById(R.id.toolbarPreStory1)).setTitle(titles[0]);

        } else {
            v = inflater.inflate(R.layout.pre_story2, container, false);
            ((Toolbar) v.findViewById(R.id.toolbarPreStory2)).setTitle(titles[1]);
        }
        return v;
    }

    @Override
    public String name() {
        return "Tab " + getArguments().getInt("position", 0);
    }

    @Override
    public boolean isOptional() {
        return true;
    }


    @Override
    public void onStepVisible() {
    }

    @Override
    public void onNext() {
        System.out.println("onNext");
    }

    @Override
    public void onPrevious() {
        System.out.println("onPrevious");
    }

    @Override
    public String optional() {
        return "You can skip";
    }

    @Override
    public boolean nextIf() {
        return i > 1;
    }
}