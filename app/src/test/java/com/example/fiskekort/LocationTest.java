package com.example.fiskekort;

import org.junit.Test;

import static org.junit.Assert.*;

public class LocationTest {
    Municipality municipality = new Municipality("Kristianstad kommun");
    Lake[] lakes = {new Lake("Titicaca", 46778754.2, 358235677.65), new Lake("Ringsjon", 28567432444.6, 3768009.1)};
    private Location lo = new Location();

    @Test
    public void setMun() {
        lo.setMun(municipality);
        assertNotNull(lo.getMun());
    }

    @Test
    public void setLakes() {
        lo.setLakes(lakes);
        assertNotNull(lo.getLakes());
    }

    @Test
    public void getMun() {
        lo.setMun(municipality);
        assertEquals(municipality, lo.getMun());
    }



    @Test
    public void getLakes() {
        lo.setLakes(lakes);
        assertArrayEquals(lakes, lo.getLakes());
    }


}