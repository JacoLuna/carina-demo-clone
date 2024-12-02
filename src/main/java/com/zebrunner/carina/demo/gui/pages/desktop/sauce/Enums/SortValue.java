package com.zebrunner.carina.demo.gui.pages.desktop.sauce.Enums;

public enum SortValue {
    NAME_AZ("az"),
    NAME_ZA("za"),
    PRICE_LOW_HIGH("lohi"),
    PRICE_HIGH_LOW("hilo");

    private String name;

    SortValue(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
