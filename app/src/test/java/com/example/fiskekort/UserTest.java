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
    }

    @Test
    public void getPassword() {
    }

    @Test
    public void setPassword() {
    }

    @Test
    public void getPhoneNumber() {
    }

    @Test
    public void setPhoneNumber() {
    }
}