package com.example.fiskekort;

public enum Duration {
    ONE_DAY(1),
    THREE_MONTHS(3),
    SIX_MONTHS(6),
    YEAR(365);


    private final int value;

    Duration(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
