package com.example.fiskekort;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LocationTest {

    Location loc = new Location();
    ArrayList <Lake> testList = new ArrayList<>();
    Lake BronaSjo = new Lake(" BronaSjo", 56.42187103024562, 13.690213257532003);


    @Test
    public void getAreas() {
        assertNotNull(loc.getAreas());
    }

    @Test
    public void getLakesByArea() {
        assertNotNull(loc.getLakesByArea("Kristianstads kommun"));
    }

    @Test
    public void getAllLakes() {
        assertNotEquals(0, loc.getAllLakes(testList).size());
    }
}