package com.topcoder.timobile.story;

public class CardDetailsModel {
    String es,matter,cardviewtitle;

    public CardDetailsModel(String es,String cardviewtitle, String matter){
        this.es=es;
        this.cardviewtitle=cardviewtitle;
        this.matter=matter;
    }

    public String getEs(){
        return this.es;
    }
    public String getCardviewtitle(){
        return this.cardviewtitle;
    }
    public String getMatter(){
        return this.matter;
    }
}
