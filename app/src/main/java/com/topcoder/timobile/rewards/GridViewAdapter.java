package com.topcoder.timobile.rewards;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.topcoder.timobile.R;
import com.topcoder.timobile.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GridViewAdapter extends BaseAdapter {
    private Context mContext;
    private JSONArray selectedArray;
    private int count;

    GridViewAdapter(Context context) {
        mContext = context;
        try {
            JSONObject obj = new JSONObject(Utils.loadJSONFromAsset(mContext, "rewards.json"));
            selectedArray = obj.getJSONArray("content");
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
            row = inflater.inflate(R.layout.grid_item_layout_rewards, parent, false);
            holder = new ViewHolder();
            holder.money = (TextView) row.findViewById(R.id.gridMoneyRewards);
            holder.name = (TextView) row.findViewById(R.id.gridNameRewards);
            holder.imageView = (ImageView) row.findViewById(R.id.gridImageRewards);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        try{
            JSONObject j = selectedArray.getJSONObject(position);
            holder.name.setText(j.getString("name"));
            holder.money.setText(j.getString("money"));
            holder.imageView.setImageResource(R.drawable.trophy);
        }catch(JSONException j){
            j.printStackTrace();
        }

        return row;
    }

    static class ViewHolder {
        TextView name;
        TextView money;
        ImageView imageView;
    }
}