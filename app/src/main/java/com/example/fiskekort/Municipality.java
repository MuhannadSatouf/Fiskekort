package com.example.fiskekort;

public class Municipality{
    private String name;

    public Municipality(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}