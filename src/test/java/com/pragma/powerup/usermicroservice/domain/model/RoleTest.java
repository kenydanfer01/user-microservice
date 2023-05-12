package com.pragma.powerup.usermicroservice.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    @Test
    public void testRoleGettersAndSetters() {
        Role role = new Role();

        role.setId(1L);
        role.setName("ROLE_ADMIN");
        role.setDescription("ROLE_ADMIN");

        assertEquals(1L, role.getId());
        assertEquals("ROLE_ADMIN", role.getName());
        assertEquals("ROLE_ADMIN", role.getDescription());
    }

    @Test
    public void testAllArgsRoleGettersAndSetters() {
        Role role = new Role(1L,"ROLE_ADMIN","ROLE_ADMIN");

        assertEquals(1L, role.getId());
        assertEquals("ROLE_ADMIN", role.getName());
        assertEquals("ROLE_ADMIN", role.getDescription());
    }
}