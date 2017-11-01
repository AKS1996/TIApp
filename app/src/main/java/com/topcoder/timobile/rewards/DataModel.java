package com.topcoder.timobile.rewards;

public class DataModel {
    String name;
    String description;
    int money;

    public DataModel(String name, String Description, int money) {
        this.description=Description;
        this.name=name;
        this.money = money;

    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getMoney() {
        return money;
    }

}