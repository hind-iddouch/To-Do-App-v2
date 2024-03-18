package com.example.Userservice.service;


import com.example.Userservice.entities.dto.UserRequestDto;
import com.example.Userservice.entities.dto.UserResponseDto;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface UserService {

    public List<UserResponseDto> getAllUsers();
    public UserResponseDto createUser(UserRequestDto userDto);
    public UserResponseDto getUserById(Long id) throws EntityNotFoundException;
    public void deleteUserById(Long id);
    public UserResponseDto updateUser( UserRequestDto userDto) throws EntityNotFoundException;




}