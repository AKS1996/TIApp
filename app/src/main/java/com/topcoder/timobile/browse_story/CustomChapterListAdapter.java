package com.topcoder.timobile.browse_story;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.topcoder.timobile.R;

import java.util.ArrayList;
import java.util.List;

public class CustomChapterListAdapter extends ArrayAdapter<String> {
    private List<String> items;
    public CustomChapterListAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
        this.items = new ArrayList<>();
        this.items.addAll(items);
    }

    @Override
    public int getCount() {
        if (items == null)
            return 0;
        return items.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.custom_chapter_list_view, parent, false);
        }

        String p = getItem(position);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.ChapTitleBrowseStoryList);
            TextView tt2 = (TextView) v.findViewById(R.id.ChapNoCircle);
            TextView tt3 = (TextView) v.findViewById(R.id.ChapProgressRatio);
            ImageView img = (ImageView) v.findViewById(R.id.ChapProgressImage);

            tt1.setText(p);
            tt2.setText(String.format("%s",position+1));

            // TODO Add Room DB for chapter progress instead of going random
            boolean progress = (Math.random() < 0.5);
            if (progress) {
                tt3.setText("15/25");
                img.setImageResource(R.drawable.circle_partial);
                v.findViewById(R.id.ChapNoCircle).setBackgroundResource(R.drawable.circle_blue);
            }else{
                tt3.setText("0/25");
                img.setImageResource(R.mipmap.ic_tick_complete);
                v.findViewById(R.id.ChapNoCircle).setBackgroundResource(R.drawable.circle_grey);
            }

        }

        return v;
    }

}