package com.topcoder.timobile.others;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.github.fcannizzaro.materialstepper.AbstractStep;
import com.topcoder.timobile.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PreStory extends AbstractStep {

    ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Utils.darkenStatusBar(getActivity(), R.color.violet);

        int i = getArguments().getInt("position", 0);
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

            /*
             *  Populate List
             */
            ListView listView = (ListView) v.findViewById(R.id.listViewPreStory);
            final List<String> staticList = loadContents(getContext(),i);
            final List<String> listContents = new ArrayList<>();
            listContents.addAll(staticList);

            adapter = new ArrayAdapter<>(v.getContext(),R.layout.pre_story_row, listContents);
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    CheckedTextView textView = (CheckedTextView) view.findViewById(R.id.checkboxPreStory);
                    if (textView.getCurrentTextColor() == getResources().getColor(R.color.navyBlue)) {
                        textView.setTextColor(Color.BLACK);
                        textView.setCheckMarkDrawable(null);
                    }
                    else{
                        textView.setTextColor(getResources().getColor(R.color.navyBlue));
                        textView.setCheckMarkDrawable(R.drawable.custom_tick_pre_story);
                    }

                }
            });

            /*
             *  Add Search Filter functionality
             *  Ideally a filter in an custom array adapter is implemented
             *  But this twitch will also work well
             */
            final EditText searchFilter = (EditText) v.findViewById(R.id.EditTextPreStory);
            searchFilter.addTextChangedListener(new TextWatcher() {
                @Override
                public void afterTextChanged(Editable arg0) {
                    String searchText = searchFilter.getText().toString().toLowerCase(Locale.getDefault());

                    if (searchText.length() > 0) {
                        listContents.clear();
                        for (String temp : staticList)
                            if (temp.toLowerCase(Locale.getDefault()).contains(searchText))
                                listContents.add(temp);
                    }
                    adapter.notifyDataSetChanged();
                }
                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1,int arg2, int arg3) {
                }
                @Override
                public void onTextChanged(CharSequence arg0, int arg1, int arg2,int arg3) {
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

    // Snack Bar error showed on if nextIf returns false
    @Override
    public String error() {
        return "<b>Check At least One Item</b>";
    }
}