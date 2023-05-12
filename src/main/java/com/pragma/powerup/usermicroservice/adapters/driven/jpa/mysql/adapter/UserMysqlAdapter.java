package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.*;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepository;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
public class UserMysqlAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public void saveUser(User user) {
        if (userRepository.findByDni(user.getDni()).isPresent()){
            throw new DniAlreadyExistsException();
        }
        if (userRepository.findByMail(user.getMail()).isPresent()){
            throw new MailAlreadyExistsException();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public void deleteUser(User user) {
        if (userRepository.findByDni(user.getDni()).isPresent()){
            userRepository.deleteById(user.getId());
        }
        else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public User getUser(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return userEntityMapper.toUser(userEntity);
    }

}
