package com.example.fiskekort;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class AreaTest {
    Municipality municipality = new Municipality("Kristianstad kommun");
    Lake[] lakes = {new Lake("Titicaca", 46778754.2, 358235677.65), new Lake("Ringsjon", 28567432444.6, 3768009.1)};
    private Area area1 = new Area();

    @Test
    public void setMun() {
        area1.setMun(municipality);
        assertNotNull(area1.getMun());
    }

    @Test
    public void setLakes() {
        area1.setLakes(lakes);
        assertNotNull(area1.getLakes());
    }

    @Test
    public void getMun() {
        area1.setMun(municipality);
        assertEquals(municipality, area1.getMun());
    }

    @Test
    public void getLakes() {
        area1.setLakes(lakes);
        assertArrayEquals(lakes, area1.getLakes());
    }
}