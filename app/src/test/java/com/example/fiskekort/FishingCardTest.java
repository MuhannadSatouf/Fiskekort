package com.example.fiskekort;

import org.junit.Test;

import static org.junit.Assert.*;

public class FishingCardTest {
<<<<<<< HEAD
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
=======
    FishingCard fc = new FishingCard("12345", "today", "tomorrow");
    private final Municipality mun = new Municipality("testMun");
    FishingCard fc2 = new FishingCard("2021-7-12", "2021-8-13", LocationType.MUNICIPALITY, mun);

    @Test
    public void constructorTest1(){
        assertNotNull(fc2);
    }

    @Test
    public void constructorTest2(){
        assertEquals(LocationType.MUNICIPALITY, fc2.getLocationType());
    }

    @Test
    public void constructorTest3(){
        assertEquals("2021-8-13", fc2.getFinishDate());
    }

    @Test
    public void constructorTest4(){
        assertEquals(mun, fc2.getMunicipality());
    }

    @Test
    public void constructorTest5(){
        assertEquals("2021-7-12", fc2.getStartDate());
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


>>>>>>> dev
}