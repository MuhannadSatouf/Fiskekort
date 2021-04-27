package com.example.fiskekort;

import org.junit.Test;

import static org.junit.Assert.*;

public class FishingCardTest {
    User testUser = new User( "MyUserName", "myEmail@gmail.com", "pa$$", "0762357845");
    FishingCard fc = new FishingCard(testUser, "12345", "today", "tomorrow");

    @Test
    public void getOwner() {
        assertEquals(testUser, fc.getOwner());
    }

    @Test
    public void setOwner() {
        User testUser2 = new User();
        fc.setOwner(testUser2);
        assertEquals(testUser2, fc.getOwner());
    }
}