package com.example.tiapp.points;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiapp.R;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private ArrayList<DataModel> data;
    private static LayoutInflater inflater=null;

    public CustomAdapter(Activity activity, ArrayList<DataModel> d) {
        data=d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        DataModel dataModel = data.get(position);
        ViewHolder viewHolder;

        if(convertView==null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_row, parent, false);
            viewHolder.textMain = (TextView) convertView.findViewById(R.id.listItemPoints1);
            viewHolder.textDescription = (TextView) convertView.findViewById(R.id.listItemPoints2);
            viewHolder.textMoney = (TextView) convertView.findViewById(R.id.listItemPointsMoney);
            viewHolder.medal = (ImageView) convertView.findViewById(R.id.imageViewPoints);

            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.textMoney.setText(dataModel.getMoney()+" pts");
        viewHolder.textDescription.setText(dataModel.getDescription());
        viewHolder.textMain.setText("Completed "+dataModel.getValue()+" "+dataModel.getType());

        if (!dataModel.isWon())
            viewHolder.medal.setImageResource(R.drawable.medal_grey);

        return convertView;
    }

    private static class ViewHolder {
        TextView textMain;
        TextView textDescription;
        TextView textMoney;
        ImageView medal;
    }
}