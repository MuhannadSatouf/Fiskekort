package com.example.fiskekort;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    User testUser = new User();

    @Test
    public void getName() {
        testUser.setName("MyName");
        assertEquals("MyName", testUser.getName());
    }

    @Test
    public void setName() {
        testUser.setName("MyName");
        assertEquals("MyName", testUser.getName());
    }

    @Test
    public void getEmail() {
        testUser.setEmail("MyName@gmail.com");
        assertEquals("MyName@gmail.com", testUser.getEmail());
    }

    @Test
    public void setEmail() {
        testUser.setEmail("MyName@gmail.com");
        assertEquals("MyName@gmail.com", testUser.getEmail());
    }

    @Test
    public void getPassword() {
        testUser.setPassword("Mypass");
        assertEquals("Mypass", testUser.getPassword());
    }

    @Test
    public void setPassword() {
        testUser.setPassword("Mypass");
        assertEquals("Mypass", testUser.getPassword());
    }

    @Test
    public void getPhoneNumber() {
        testUser.setPhoneNumber("07623586145");
        assertEquals("07623586145", testUser.getPhoneNumber());
    }

    @Test
    public void setPhoneNumber() {
        testUser.setPhoneNumber("07623586145");
        assertEquals("07623586145", testUser.getPhoneNumber());
    }
}