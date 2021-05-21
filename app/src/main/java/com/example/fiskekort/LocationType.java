package com.example.fiskekort;

public enum LocationType {
    MUNICIPALITY("Municipality"),
    WATER("Single Lake");


    private final String value;

    LocationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
