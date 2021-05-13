package com.example.fiskekort;

import org.junit.Test;

import java.util.Arrays;

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
/*
    @Test
    public void testGetLakesByArea() {
        Municipality mun = loc.getAreas().get(0).getMun();
        assertNotNull(loc.getLakesByArea(mun));
    }
*/
    @Test
    public void getAllLakes() {
        assertNotNull(loc.getAllLakes());
    }
}