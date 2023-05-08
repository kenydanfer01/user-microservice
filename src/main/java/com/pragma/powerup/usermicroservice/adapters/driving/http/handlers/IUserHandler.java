package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;

import java.util.List;

public interface IUserHandler {
    void saveUser(UserRequestDto userRequestDto);
    void deleteUser(UserRequestDto userRequestDto);

    List<UserResponseDto> getAllOwners(Integer page);
    List<UserResponseDto> getAllEmployees(int page);
    List<UserResponseDto> getAllClients(int page);

    UserResponseDto getOwner(Long id);
    UserResponseDto getEmployee(Long id);
    UserResponseDto getClient(Long id);
    UserResponseDto getUser(Long id);

}
