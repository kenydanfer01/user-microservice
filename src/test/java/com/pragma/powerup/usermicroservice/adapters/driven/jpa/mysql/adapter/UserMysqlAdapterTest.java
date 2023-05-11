package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.UnderageUserException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.UserAlreadyExistsException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepository;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.services.IAgeValidationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserMysqlAdapterTest {

    @InjectMocks
    UserMysqlAdapter userMysqlAdapter;

    @Mock
    IUserRepository userRepository;

    @Mock
    IUserEntityMapper userEntityMapper;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    IAgeValidationService ageValidationService;

    User user;
    UserEntity userEntity;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        Role role = new Role(1L, "ROLE_OWNER", "ROLE_OWNER");

        user = new User(1L,"Name","surname", "7484",
                "789", LocalDate.of(2000, 1, 1),
                "mail@mail.pe", "123", role);

        userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setDni("12345678");
        userEntity.setBirthday(LocalDate.of(2000, 1, 1));
        userEntity.setPassword("password");

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setName("OWNER");
        userEntity.setRoleEntity(roleEntity);
    }

    @Test
    void saveUserTest() {
        when(userRepository.findByDni(user.getDni())).thenReturn(Optional.empty());
        when(ageValidationService.isAdult(user.getBirthday())).thenReturn(true);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userEntityMapper.toEntity(user)).thenReturn(userEntity);

        userMysqlAdapter.saveUser(user);

        Assertions.assertEquals("encodedPassword", user.getPassword());
    }

    @Test
    void saveUserAlreadyExistsTest() {
        when(userRepository.findByDni(user.getDni())).thenReturn(Optional.of(userEntity));

        Assertions.assertThrows(UserAlreadyExistsException.class, () -> {
            userMysqlAdapter.saveUser(user);
        });
    }

    @Test
    void saveUserUnderageTest() {
        when(userRepository.findByDni(user.getDni())).thenReturn(Optional.empty());
        OngoingStubbing<Boolean> booleanOngoingStubbing = when(ageValidationService.isAdult(user.getBirthday())).thenReturn(false);

        Assertions.assertThrows(UnderageUserException.class, () -> {
            userMysqlAdapter.saveUser(user);
        });
    }
}