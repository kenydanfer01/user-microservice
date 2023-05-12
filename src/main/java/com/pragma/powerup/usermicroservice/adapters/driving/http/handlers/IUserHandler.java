package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;

import java.util.List;

public interface IUserHandler {

    void saveUser(UserRequestDto userRequestDto);

    void deleteUser(UserRequestDto userRequestDto);

    UserResponseDto getUser(Long id);
}
