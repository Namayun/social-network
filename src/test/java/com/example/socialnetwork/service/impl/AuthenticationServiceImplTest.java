package com.example.socialnetwork.service.impl;


import com.example.socialnetwork.dto.login.LoginRequestDto;
import com.example.socialnetwork.dto.login.LoginResponse;
import com.example.socialnetwork.entity.user.UserEntity;
import com.example.socialnetwork.repository.UserEntityRepository;
import com.example.socialnetwork.security.JwtService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceImplTest {
    @Mock
    private  UserEntityRepository  userEntityRepository;

    @Mock
    private JwtService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @Test
    void testAuthenticate_ShouldAuthenticateSuccessfully() {
        LoginRequestDto loginRequestDto = new LoginRequestDto();
        loginRequestDto.setUsername("username");
        loginRequestDto.setPassword("password");

        UserEntity user = new UserEntity();
        user.setUsername("testUser");

        String jwtToken = "<PASSWORD>";
       LocalDateTime expirationTime = LocalDateTime.now();
       expirationTime.plusNanos(36000000000000L);



        Mockito.when(authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername().toLowerCase().trim(), loginRequestDto.getPassword()
                ))).thenReturn(any());

        Mockito.when(userEntityRepository.findByUsername(loginRequestDto.getUsername())).thenReturn(Optional.of(user));

        Mockito.when(jwtService.generateToken(user)).thenReturn(jwtToken);
        Mockito.when(jwtService.getExpirationTime()).thenReturn(expirationTime);

        LoginResponse result = authenticationService.authenticate(loginRequestDto);

        Assertions.assertEquals(jwtToken,result.getToken());
        Assertions.assertEquals(expirationTime,result.getExpiresIn());

        Mockito.verify(authenticationManager, Mockito.times(1)).authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername().toLowerCase().trim(), loginRequestDto.getPassword()
                )
        );

        Mockito.verify(userEntityRepository, Mockito.times(1)).findByUsername(loginRequestDto.getUsername());
        Mockito.verify(jwtService, Mockito.times(1)).generateToken(user);
        Mockito.verify(jwtService, Mockito.times(1)).getExpirationTime();
        Mockito.verifyNoMoreInteractions(authenticationManager, userEntityRepository, jwtService);

    }

}