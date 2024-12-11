package com.zebrunner.carina.demo.mobile.gui.pages.android.apptoide.enums;

public enum TabValue {
    HOME("Home"),
    EDITORIAL("Editorial"),
    SEARCH("Search"),
    STORES("Stores"),
    APPS("Apps");

    private final String value;

    TabValue(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
