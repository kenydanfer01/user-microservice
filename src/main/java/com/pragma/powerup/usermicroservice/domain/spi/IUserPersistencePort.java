package com.pragma.powerup.usermicroservice.domain.spi;

import com.pragma.powerup.usermicroservice.domain.model.User;

public interface IUserPersistencePort {

    void saveUser(User user);

    void deleteUser(User user);

    User getUser(Long id);
}
