package com.topcoder.timobile;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.Toolbar;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.github.fcannizzaro.materialstepper.AbstractStep;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PreStory extends AbstractStep {

    private int i = 1;
    ArrayAdapter<String> adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Utils.darkenStatusBar(getActivity(),R.color.violet);

        i = getArguments().getInt("position", 0);
        View v;
        if (i == 1) {
            v = inflater.inflate(R.layout.pre_story1, container, false);
            ((TextView) v.findViewById(R.id.toolbarPreStory1)).setText(R.string.title1);

        } else {
            v = inflater.inflate(R.layout.pre_story2, container, false);
            if (i==2)
                ((TextView) v.findViewById(R.id.toolbarPreStory2)).setText(R.string.title2);
            else
                ((TextView) v.findViewById(R.id.toolbarPreStory2)).setText(R.string.title3);

            ListView listView = (ListView) v.findViewById(R.id.listViewPreStory);
            List<String> listContents = loadContents(getContext(),i);
            adapter = new ArrayAdapter<>(v.getContext(),R.layout.pre_story_row, listContents);
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ((CheckedTextView) view.findViewById(R.id.checkboxPreStory)).setTextColor(getResources().getColor(R.color.navyBlue));
                    ((CheckedTextView) view.findViewById(R.id.checkboxPreStory)).setCheckMarkDrawable(R.drawable.custom_tick_pre_story);
                }
            });

        }
        return v;
    }

    private List<String> loadContents(Context context, int position){
        List<String> listContents = new ArrayList<>();
        try{
            JSONObject jsonObject = new JSONObject(Utils.loadJSONFromAsset(context,"prestory.json"));
            JSONArray jsonArray = jsonObject.getJSONArray(position+"");
            for (int i = 0; i < jsonArray.length(); i++)
                listContents.add(jsonArray.getString(i));
        }catch (JSONException j){
            Log.e(Utils.TAG, j.getMessage());
        }

        return listContents;
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