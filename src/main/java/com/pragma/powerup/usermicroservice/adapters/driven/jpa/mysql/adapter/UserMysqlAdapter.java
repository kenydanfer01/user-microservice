package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.*;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepository;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.services.IAgeValidationService;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.pragma.powerup.usermicroservice.configuration.Constants.*;

@RequiredArgsConstructor
@Transactional
public class UserMysqlAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final IUserEntityMapper userEntityMapper;
    private final IAgeValidationService ageValidationService;


    @Override
    public void saveUser(User user) {
        if (userRepository.findByDni(user.getDni()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        if (!ageValidationService.isAdult(user.getBirthday())){
            throw new UnderageUserException();
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
    public List<User> getAllOwners(int page) {
        Pageable pagination = PageRequest.of(page, MAX_PAGE_SIZE);
        List<UserEntity> userEntityList = userRepository.findAllByRoleEntityId(OWNER_ROLE_ID, pagination);
        if (userEntityList.isEmpty()){
            throw new NoDataFoundException();
        }
        return userEntityMapper.toUserList(userEntityList);
    }

    @Override
    public List<User> getAllEmployees(int page) {
        Pageable pagination = PageRequest.of(page, MAX_PAGE_SIZE);
        List<UserEntity> userEntityList = userRepository.findAllByRoleEntityId(EMPLOYEE_ROLE_ID, pagination);
        if (userEntityList.isEmpty()){
            throw new NoDataFoundException();
        }
        return userEntityMapper.toUserList(userEntityList);
    }

    @Override
    public List<User> getAllClients(int page) {
        Pageable pagination = PageRequest.of(page, MAX_PAGE_SIZE);
        List<UserEntity> userEntityList = userRepository.findAllByRoleEntityId(CLIENT_ROLE_ID, pagination);
        if (userEntityList.isEmpty()){
            throw new NoDataFoundException();
        }
        return userEntityMapper.toUserList(userEntityList);
    }

    @Override
    public User getOwner(Long id) {
        UserEntity userEntity = userRepository.findByIdAndRoleEntityId(id, OWNER_ROLE_ID).orElseThrow(UserNotFoundException::new);
        return userEntityMapper.toUser(userEntity);
    }

    @Override
    public User getEmployee(Long id) {
        UserEntity userEntity = userRepository.findByIdAndRoleEntityId(id, EMPLOYEE_ROLE_ID).orElseThrow(UserNotFoundException::new);
        return userEntityMapper.toUser(userEntity);
    }

    @Override
    public User getClient(Long id) {
        UserEntity userEntity = userRepository.findByIdAndRoleEntityId(id, CLIENT_ROLE_ID).orElseThrow(UserNotFoundException::new);
        return userEntityMapper.toUser(userEntity);
    }

    @Override
    public User getUser(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return userEntityMapper.toUser(userEntity);
    }
}
