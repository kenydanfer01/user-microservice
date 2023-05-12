package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.UserIsMinorException;
import com.pragma.powerup.usermicroservice.domain.exceptions.UserIsNullException;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import com.pragma.powerup.usermicroservice.domain.validations.AgeValidation;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void saveUser(User user) {
        if (user == null){
            throw new UserIsNullException();
        }
        if (!AgeValidation.isAdult(user.getBirthday())){
            throw new UserIsMinorException();
        }
        userPersistencePort.saveUser(user);
    }

    @Override
    public void deleteUser(User user) {
        userPersistencePort.deleteUser(user);
    }

    @Override
    public User getUser(Long id) {
        return userPersistencePort.getUser(id);
    }
}
