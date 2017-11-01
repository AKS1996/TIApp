package com.topcoder.timobile.story;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.topcoder.timobile.R;

import java.util.List;

class CustomCardAdapter extends ArrayAdapter<CardDetailsModel> {
    public CustomCardAdapter(Context context,int layoutId,List<CardDetailsModel> list){
        super(context,layoutId,list);
    }

    public View getView(int Position,View convertview,ViewGroup parent){
        View view;
        if(convertview==null){
            view=LayoutInflater.from(getContext()).inflate(R.layout.custom_card_adapter,null);

        }else{
            view=convertview;
        }
        CardDetailsModel mCardDetailsModel=getItem(Position);
        if(mCardDetailsModel!=null){
            TextView es=(TextView)view.findViewById(R.id.es);
            TextView matter=(TextView)view.findViewById(R.id.matter);
            TextView cardviewtitle=(TextView)view.findViewById(R.id.card_view_title);
            ImageView imageView=(ImageView)view.findViewById(R.id.image);
            imageView.setImageResource(R.mipmap.ic_launcher);
            es.setText(mCardDetailsModel.getEs());
            matter.setText(mCardDetailsModel.getMatter());
            cardviewtitle.setText(mCardDetailsModel.getCardviewtitle());
        }
        return view;
    }
}

