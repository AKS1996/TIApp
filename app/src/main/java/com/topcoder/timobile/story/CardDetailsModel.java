package com.topcoder.timobile.story;

class CardDetailsModel {
    private String es,matter,cardviewtitle;
    private int cards, chapters;

    CardDetailsModel(String es,String cardviewtitle,int cards, int chapters, String matter){
        this.es=es;
        this.cardviewtitle=cardviewtitle;
        this.matter=matter;
        this.cards = cards;
        this.chapters = chapters;
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
    
}
