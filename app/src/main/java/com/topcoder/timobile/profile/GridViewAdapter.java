package com.topcoder.timobile.profile;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.topcoder.timobile.R;
import com.topcoder.timobile.others.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GridViewAdapter extends BaseAdapter {
    private Context mContext;
    private JSONArray selectedArray;
    private int count;

    GridViewAdapter(Context c,int section) {
        mContext = c;
        try {
            JSONObject obj = new JSONObject(Utils.loadJSONFromAsset(mContext, "profile.json"));
            selectedArray = obj.getJSONArray(section+"");
            count = selectedArray.length();
        }catch(JSONException j){
            j.printStackTrace();
        }
    }

    public int getCount() {
        return count;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(R.layout.grid_item_layout, parent, false);
            holder = new ViewHolder();
            holder.titleTextView = (TextView) row.findViewById(R.id.grid_item_title);
            holder.imageView = (ImageView) row.findViewById(R.id.grid_item_image);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        try{
            JSONObject j = selectedArray.getJSONObject(position);
            holder.titleTextView.setText(j.getString("name"));

            if (j.getString("won").equals("true"))
                holder.imageView.setImageResource(R.drawable.trophy);
            else
                holder.imageView.setImageResource(R.drawable.ic_search_black_24dp);


        }catch(JSONException j){
            j.printStackTrace();
        }

        return row;
    }

    static class ViewHolder {
        TextView titleTextView;
        ImageView imageView;
    }
}