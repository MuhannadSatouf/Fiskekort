package com.example.fiskekort;

public enum LocationType {
    MUNICIPALITY ("Kommun"),
    WATER("Sjö");


    private final String value;

    LocationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
