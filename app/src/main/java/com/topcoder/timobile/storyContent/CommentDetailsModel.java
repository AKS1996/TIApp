package com.topcoder.timobile.storyContent;

public class CommentDetailsModel {
    String title,comment;

    public CommentDetailsModel(String title, String comment){
        this.title=title;
        this.comment=comment;
    }
    public String getTitle(){
        return this.title;
    }
    public String getComment(){
        return this.comment;
    }
}
