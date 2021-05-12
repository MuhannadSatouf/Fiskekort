package com.example.fiskekort;

import org.junit.Test;

import static org.junit.Assert.*;

public class LakeTest {
    private final String name = "Titicaca";
    private final double lat = 3869753.21;
    private final double lon = 156543433.2;
    private final Lake lake = new Lake(name, lat, lon);

    @Test
    public void getName() {
        assertEquals(name, lake.getName());
    }

    @Test
    public void getLatitude() {
        assertEquals(lat, lake.getLatitude(), 0);
    }

    @Test
    public void getLongitude() {
        assertEquals(lon, lake.getLongitude(), 0);
    }
}