package com.example.socialnetwork.controller;

import com.example.socialnetwork.dto.login.LoginRequestDto;
import com.example.socialnetwork.dto.login.LoginResponse;
import com.example.socialnetwork.dto.request.UserRequestDto;
import com.example.socialnetwork.dto.response.UserResponseDto;
import com.example.socialnetwork.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> register(
            @Valid @RequestBody UserRequestDto userRequestDto
    ) {
        return ResponseEntity.ok(authenticationService.signup(userRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(
            @Valid @RequestBody LoginRequestDto loginRequestDto
    ) {

        return ResponseEntity.ok(authenticationService.authenticate(loginRequestDto));
    }
}
