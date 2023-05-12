package com.pragma.powerup.usermicroservice.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void testUserGettersAndSetters() {
        User user = new User();
        Role role = new Role();

        user.setId(1L);
        user.setName("Test");
        user.setSurname("User");
        user.setDni("12345678");
        user.setPhone("1234567890");
        user.setBirthday(LocalDate.of(2000, 1, 1));
        user.setMail("test@example.com");
        user.setPassword("password");
        user.setRole(role);

        assertEquals(1L, user.getId());
        assertEquals("Test", user.getName());
        assertEquals("User", user.getSurname());
        assertEquals("12345678", user.getDni());
        assertEquals("1234567890", user.getPhone());
        assertEquals(LocalDate.of(2000, 1, 1), user.getBirthday());
        assertEquals("test@example.com", user.getMail());
        assertEquals("password", user.getPassword());
        assertEquals(role, user.getRole());
    }

    @Test
    public void testUserAllArgsGettersAndSetters() {
        Role role = new Role();
        User user = new User(1L, "Test", "User", "12345678", "1234567890",
                LocalDate.of(2000, 1, 1), "test@example.com", "password", role);

        assertEquals(1L, user.getId());
        assertEquals("Test", user.getName());
        assertEquals("User", user.getSurname());
        assertEquals("12345678", user.getDni());
        assertEquals("1234567890", user.getPhone());
        assertEquals(LocalDate.of(2000, 1, 1), user.getBirthday());
        assertEquals("test@example.com", user.getMail());
        assertEquals("password", user.getPassword());
        assertEquals(role, user.getRole());
    }
}