package com.example.fiskekort;

import org.junit.Test;

import static org.junit.Assert.*;

public class MunicipalityTest {
    private final String name = "Kristianstad kommun";
    private final Municipality m = new Municipality(name);
    private final String id = "ft#7gdfeeGDrhj";

    @Test
    public void getName() {
        assertEquals(name, m.getName());
    }

    @Test
    public void getFdID() {
        m.setFdID(id);
        assertEquals(id, m.getFdID());
    }

    @Test
    public void setFdID() {
        m.setFdID(id);
        assertEquals(id, m.getFdID());
    }
}