package com.topcoder.timobile.story;

public class CardDetailsModel {
    private String es,matter,cardviewtitle;
    private int cards, chapters,Id;

    public CardDetailsModel(String es,String cardviewtitle,int cards, int chapters, String matter,int id){
        this.es=es;
        this.cardviewtitle=cardviewtitle;
        this.matter=matter;
        this.cards = cards;
        this.chapters = chapters;
        this.Id=id;
    }

    String getEs(){
        return this.es;
    }
    String getCardviewtitle(){
        return this.cardviewtitle;
    }
    String getMatter(){
        return this.matter;
    }
    int getCardCount(){
        return this.cards;
    }
    int getChapterCount(){
        return this.chapters;
    }
    public int getId(){
        return this.Id;
    }
    
}
