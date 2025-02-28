package com.example.socialnetwork.controller;




import com.example.socialnetwork.dto.response.UserResponseDto;
import com.example.socialnetwork.dto.update.UpdatePasswordDto;
import com.example.socialnetwork.dto.update.UserUpdateProfileDto;
import com.example.socialnetwork.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @PostMapping("/update/password")
    public ResponseEntity<String> updatePassword(
            @Valid @RequestBody UpdatePasswordDto updatePasswordDto
    ) {
        return ResponseEntity.ok(userService.updatePassword(updatePasswordDto));
    }


    @PostMapping("/update/profile")
    public ResponseEntity<UserResponseDto> updateProfile(
            @Valid @RequestBody UserUpdateProfileDto update
    ) {
        return ResponseEntity.ok(userService.updateProfile(update));
    }
}
