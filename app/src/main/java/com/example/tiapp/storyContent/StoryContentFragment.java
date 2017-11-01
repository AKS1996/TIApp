package com.example.tiapp.storyContent;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiapp.R;
import com.example.tiapp.Utils;
import com.github.fcannizzaro.materialstepper.AbstractStep;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StoryContentFragment extends AbstractStep {

    private int chapterPosition;
    private int storyNumber;

    /**
     *  Adds page title, chapter title, image visibility, chapter content to StoryContent layout
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Utils.darkenStatusBar(getActivity(), R.color.violet);
        chapterPosition = getArguments().getInt("position");
        storyNumber = getArguments().getInt(getResources().getString(R.string.story_position));

        // TODO Add title
        // subtitle, image visibility, chap title, content done
        View v = inflater.inflate(R.layout.activity_story_content, container, false);
        try {
            JSONObject obj = new JSONObject(Utils.loadJSONFromAsset(getContext(), "stories.json"));
            JSONObject storySelected = obj.getJSONObject(storyNumber+"");
            JSONObject chapter = storySelected.getJSONArray("chapters").getJSONObject(chapterPosition);
            ((TextView) v.findViewById(R.id.SubtitleStoryContent)).setText(storySelected.getString("subtitle"));

            if (chapterPosition == 1)
                v.findViewById(R.id.ChapVideoImage).setVisibility(View.VISIBLE);

            ((TextView) v.findViewById(R.id.TitleStoryContent)).setText(chapter.getString("titleChapter"));
            ((TextView) v.findViewById(R.id.ContentStoryContent)).setText(chapter.getString("content"));
            v.findViewById(R.id.floatingCommentsButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showCommentsDialog(view.getContext());

                }
            });


        }catch(JSONException j){
            j.printStackTrace();
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
        return chapterPosition > 1;
    }

    CustomAdapter customAdapter;
    private void showCommentsDialog(final Context context){
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View v =getLayoutInflater().inflate(R.layout.custom_comments_dialog, null);
        builder.setView(v);
        final AlertDialog dialog = builder.create();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;

        dialog.show();
        dialog.getWindow().setAttributes(lp);
        ImageButton continueStory=(ImageButton)dialog.findViewById(R.id.cancel_button_comments);
        continueStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();

            }
        });

        ListView mCommentsList=(ListView)dialog.findViewById(R.id.comments_list);
        ArrayList<CommentDetailsModel> list=new ArrayList<>();


        customAdapter=new CustomAdapter(context,R.layout.commentsdialog_adapter,
                list);
        mCommentsList.setAdapter(customAdapter);

        Button postcomment=(Button)dialog.findViewById(R.id.post_comment);

        postcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(((EditText)dialog.findViewById(R.id.comment_to_post)).getText().toString().isEmpty()){
                    Toast.makeText(context,"postcomment",Toast.LENGTH_SHORT).show();
                }else{

                    customAdapter.insert(new CommentDetailsModel("shefh",((EditText)dialog.findViewById(R.id.comment_to_post)).getText()
                            .toString()),0);
//                    Toast.makeText(getBaseContext(),"pmment",Toast.LENGTH_SHORT).show();
                    ((EditText)dialog.findViewById(R.id.comment_to_post)).setText("");
                }
            }
        });
    }

}