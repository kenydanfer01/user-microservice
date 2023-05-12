package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.domain.exceptions.UserIsMinorException;
import com.pragma.powerup.usermicroservice.domain.exceptions.UserIsNullException;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

        @Mock
        private IUserPersistencePort userPersistencePort;

        private UserUseCase userUseCase;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.openMocks(this);
            userUseCase = new UserUseCase(userPersistencePort);
        }

        @Test
        public void saveUser_shouldThrowException_whenUserIsNull() {
            assertThrows(UserIsNullException.class, () -> userUseCase.saveUser(null));
        }

        @Test
        public void saveUser_shouldThrowException_whenUserIsUnderage() {
            User underageUser = new User();
            underageUser.setBirthday(LocalDate.now().minusYears(10)); // 10 years old

            assertThrows(UserIsMinorException.class, () -> userUseCase.saveUser(underageUser));
        }

        @Test
        public void saveUser_shouldCallSaveUserOnPersistencePort_whenUserIsValid() {
            User adultUser = new User();
            adultUser.setBirthday(LocalDate.now().minusYears(20)); // 20 years old

            userUseCase.saveUser(adultUser);

            verify(userPersistencePort, times(1)).saveUser(adultUser);
        }

        @Test
        public void deleteUser_shouldCallDeleteUserOnPersistencePort() {
            User user = new User();

            userUseCase.deleteUser(user);

            verify(userPersistencePort, times(1)).deleteUser(user);
        }

    @Test
    public void getUser_shouldReturnUser_whenIdIsValid() {
        User user = new User();
        user.setId(1L);
        when(userPersistencePort.getUser(1L)).thenReturn(user);
        User result = userUseCase.getUser(1L);
        assertEquals(user, result);
        verify(userPersistencePort).getUser(1L);
    }

}