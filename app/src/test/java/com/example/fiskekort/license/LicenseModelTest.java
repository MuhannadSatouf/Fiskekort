package com.example.fiskekort.license;

import org.junit.Test;

import static org.junit.Assert.*;

public class LicenseModelTest {

    LicenseModel licenseModel = new LicenseModel("Hunting license", "123", "A license for going hunting in SKANE", 4);

    @Test
    public void getName() {
        assertEquals("Hunting license", licenseModel.getName());
    }

    @Test
    public void getId() {
        assertEquals("123", licenseModel.getId());
    }

    @Test
    public void getDesc() {
        assertEquals("A license for going hunting in SKANE", licenseModel.getDesc());
    }

    @Test
    public void getView() {
        assertEquals(4, licenseModel.getView());
    }
}