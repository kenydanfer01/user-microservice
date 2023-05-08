package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserRequestMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserResponseMapper;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserHandlerImpl implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;


    @Override
    public void saveUser(UserRequestDto userRequestDto) {
        userServicePort.saveUser(userRequestMapper.toUser(userRequestDto));
    }

    @Override
    public void deleteUser(UserRequestDto userRequestDto) {
        userServicePort.deleteUser(userRequestMapper.toUser(userRequestDto));
    }

    @Override
    public List<UserResponseDto> getAllOwners(Integer page) {
        return userResponseMapper.toResponseList(userServicePort.getAllOwners(page));
    }

    @Override
    public List<UserResponseDto> getAllEmployees(int page) {
        return userResponseMapper.toResponseList(userServicePort.getAllEmployees(page));
    }

    @Override
    public List<UserResponseDto> getAllClients(int page) {
        return userResponseMapper.toResponseList(userServicePort.getAllClients(page));
    }

    @Override
    public UserResponseDto getOwner(Long id) {
        return userResponseMapper.toResponse(userServicePort.getOwner(id));
    }

    @Override
    public UserResponseDto getEmployee(Long id) {
        return userResponseMapper.toResponse(userServicePort.getEmployee(id));
    }

    @Override
    public UserResponseDto getClient(Long id) {
        return userResponseMapper.toResponse(userServicePort.getClient(id));
    }

    @Override
    public UserResponseDto getUser(Long id) {
        return userResponseMapper.toResponse(userServicePort.getUser(id));
    }
}
