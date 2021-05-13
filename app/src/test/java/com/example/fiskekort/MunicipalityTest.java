package com.example.fiskekort;

import org.junit.Test;

import static org.junit.Assert.*;

public class MunicipalityTest {
    private final String name = "Kristianstad kommun";
    private final Municipality m = new Municipality(name);

    @Test
    public void getName() {
        assertEquals(name, m.getName());
    }

}