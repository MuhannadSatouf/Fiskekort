package com.example.fiskekort;

import org.junit.Test;

import static org.junit.Assert.*;

public class FishingCardTest {
    User testUser = new User( "MyUserName", "myEmail@gmail.com", "pa$$", "0762357845");
    FishingCard fc = new FishingCard(testUser, "12345", "today", "tomorrow");

    @Test
    public void getOwner() {

        assertEquals(testUser,fc.getOwner());
    }

    @Test
    public void setOwner() {
        System.out.println("Unit Tests are being run");
        User testUser2 = new User();
        fc.setOwner(testUser2);
        assertEquals(testUser2, fc.getOwner());
    }
    @Test
    public void setCardNumber(){
        fc.setCardNumber("54321");
        assertEquals("54321",fc.getCardNumber());
    }
    @Test
    public void getCardNumber(){
        fc.setCardNumber("54321");
        assertEquals("54321",fc.getCardNumber());


    }
    @Test
    public void setStartDate(){

        assertEquals("today",fc.getStartDate());

    }
    @Test
    public void getStartDate(){
        fc.setStartDate("today");
        assertEquals("today",fc.getStartDate());
    }
    @Test
    public void setFinishDate(){
        fc.setFinishDate("tomorrow");
        assertEquals("tomorrow",fc.getFinishDate());
    }
    @Test
    public void getFinishDate(){

        assertEquals("tomorrow",fc.getFinishDate());
    }


}