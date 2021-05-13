package com.example.fiskekort;

import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {

    Location loc = new Location();
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
        assertNotEquals(0, loc.getAllLakes().size());
    }
}