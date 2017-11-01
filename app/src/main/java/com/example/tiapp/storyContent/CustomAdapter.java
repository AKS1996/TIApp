package com.example.tiapp.storyContent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tiapp.R;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<CommentDetailsModel> {
    public CustomAdapter(Context context, int layoutId, List<CommentDetailsModel> list){
        super(context,layoutId,list);
    }

    public View getView(int Position, View convertview, ViewGroup parent){
        View view;
        if(convertview==null){
            view= LayoutInflater.from(getContext()).inflate(R.layout.commentsdialog_adapter,null);

        }else{
            view=convertview;
        }
        CommentDetailsModel mCommentDetails=getItem(Position);
        if(mCommentDetails!=null){
            TextView commenttitle=(TextView)view.findViewById(R.id.comment_heading);
            TextView comment=(TextView)view.findViewById(R.id.comment);
            ImageView imageView=(ImageView)view.findViewById(R.id.comment_image);
            imageView.setImageResource(R.drawable.profile);
            commenttitle.setText(mCommentDetails.getTitle());
            comment.setText(mCommentDetails.getComment());
        }
        return view;
    }
}