package com.example.socialnetwork.service.impl;

import com.example.socialnetwork.dto.login.LoginRequestDto;
import com.example.socialnetwork.dto.login.LoginResponse;
import com.example.socialnetwork.dto.request.UserRequestDto;
import com.example.socialnetwork.dto.response.UserResponseDto;
import com.example.socialnetwork.entity.user.UserEntity;
import com.example.socialnetwork.exception.AuthenticationException;
import com.example.socialnetwork.mapper.UserMapper;
import com.example.socialnetwork.repository.UserEntityRepository;
import com.example.socialnetwork.security.JwtService;
import com.example.socialnetwork.service.AuthenticationService;
import jakarta.persistence.EntityExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserEntityRepository userEntityRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity getAuthenticatedUser() throws AuthenticationException {
        String loggedInUser = SecurityContextHolder.getContext().getAuthentication().getName();
        return userEntityRepository.findByUsername(loggedInUser)
                .orElseThrow(
                        () -> new AuthenticationException("User not authenticated")
                );

    }

    @Transactional
    @Override
    public UserResponseDto signup(UserRequestDto userRequestDto) {
        if (userEntityRepository.findByUsername(userRequestDto.getUsername()).isPresent()) {

            throw new EntityExistsException("Username already exists");
        }
        if (userEntityRepository.findByEmail(userRequestDto.getEmail()).isPresent()) {
            throw new EntityExistsException("Email already exists");
        }

        UserEntity newUser = userEntityRepository.saveAndFlush(UserMapper.toEntity(userRequestDto));
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return UserMapper.toResponse(newUser);
    }

    @Transactional
    @Override
    public LoginResponse authenticate(LoginRequestDto login) {
        if (Objects.isNull(login.getUsername()) || Objects.isNull(login.getPassword())) {
            throw new AuthenticationException("Username or password is empty");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getUsername().toLowerCase().trim(), login.getPassword()
                )
        );
        UserEntity authenticatedUser = userEntityRepository.findByUsername(
                login.getUsername().toLowerCase()).orElseThrow(
                () -> new EntityExistsException("User not found")
        );
        String jwtToken = jwtService.generateToken(authenticatedUser);

        return new LoginResponse(jwtToken, jwtService.getExpirationTime());
    }
}
