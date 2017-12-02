package com.topcoder.timobile.story;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.topcoder.timobile.R;

import java.util.ArrayList;
import java.util.List;


public class Search extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ListView listView = (ListView) findViewById(R.id.SearchList);
        List<String> contents = new ArrayList<>();
        contents.add("Win Star Farm");
        contents.add("Lorem Ipsum");
        contents.add("Elor Demat");

        final ArrayAdapter listAdapter = new ArrayAdapter<>(this, R.layout.custom_search_list_item, contents);
        listView.setAdapter(listAdapter);

    }

}
