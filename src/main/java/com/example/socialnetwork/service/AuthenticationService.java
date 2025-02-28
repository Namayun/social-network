package com.example.socialnetwork.service;

import com.example.socialnetwork.dto.login.LoginRequestDto;
import com.example.socialnetwork.dto.login.LoginResponse;
import com.example.socialnetwork.dto.request.UserRequestDto;
import com.example.socialnetwork.dto.response.UserResponseDto;
import com.example.socialnetwork.entity.user.UserEntity;

public interface AuthenticationService {
     UserEntity getAuthenticatedUser();
     UserResponseDto signup(UserRequestDto userRequestDto);
     LoginResponse authenticate(LoginRequestDto login);

}
