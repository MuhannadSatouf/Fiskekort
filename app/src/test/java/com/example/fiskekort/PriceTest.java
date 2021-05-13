package com.example.fiskekort;

import org.junit.Test;

import static org.junit.Assert.*;

public class PriceTest {
    Price p = new Price();

    @Test
    public void getPrice1() {
        double price = p.getPrice(Duration.ONE_DAY, LocationType.MUNICIPALITY);
        assertEquals(80, price, 0);
    }

    @Test
    public void getPrice2() {
        double price = p.getPrice(Duration.ONE_DAY, LocationType.WATER);
        assertEquals(80, price, 0);
    }

    @Test
    public void getPrice3() {
        double price = p.getPrice(Duration.THREE_MONTHS, LocationType.MUNICIPALITY);
        assertEquals(250, price, 0);
    }

    @Test
    public void getPrice4() {
        double price = p.getPrice(Duration.THREE_MONTHS, LocationType.WATER);
        assertEquals(150, price, 0);
    }

    @Test
    public void getPrice5() {
        double price = p.getPrice(Duration.SIX_MONTHS, LocationType.MUNICIPALITY);
        assertEquals(400, price, 0);
    }

    @Test
    public void getPrice6() {
        double price = p.getPrice(Duration.SIX_MONTHS, LocationType.WATER);
        assertEquals(250, price, 0);
    }

    @Test
    public void getPrice7() {
        double price = p.getPrice(Duration.YEAR, LocationType.MUNICIPALITY);
        assertEquals(600, price, 0);
    }

    @Test
    public void getPrice8() {
        double price = p.getPrice(Duration.YEAR, LocationType.WATER);
        assertEquals(400, price, 0);
    }
}