package com.topcoder.timobile.points;

public class DataModel {

    boolean won;
    int value;
    String type;
    String description;
    int money;

    public DataModel(boolean won, String type, int value, String Description, int money) {
        this.won=won;
        this.type=type;
        this.description=Description;
        this.value=value;
        this.money = money;

    }

    public boolean isWon() {
        return won;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public int getValue() {
        return value;
    }
    public int getMoney() {
        return money;
    }

}