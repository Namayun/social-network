package com.example.socialnetwork.service.impl;


import com.example.socialnetwork.dto.response.UserResponseDto;
import com.example.socialnetwork.dto.update.UpdatePasswordDto;
import com.example.socialnetwork.dto.update.UserUpdateProfileDto;
import com.example.socialnetwork.entity.user.UserEntity;
import com.example.socialnetwork.mapper.UserMapper;
import com.example.socialnetwork.repository.UserEntityRepository;
import com.example.socialnetwork.service.AuthenticationService;
import com.example.socialnetwork.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserEntityRepository userEntityRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationService authenticationService;


    @Modifying
    @Transactional
    @Override
    public UserResponseDto updateProfile(UserUpdateProfileDto userUpdateProfileDto) {
        UserEntity existUser = authenticationService.getAuthenticatedUser();
        UserMapper.updateEntityFromDto(existUser, userUpdateProfileDto);
        return UserMapper.toResponse(userEntityRepository.save(existUser));
    }

    @Transactional
    @Modifying
    @Override
    public String updatePassword(UpdatePasswordDto updatePasswordDto) {
        UserEntity existUser = authenticationService.getAuthenticatedUser();
        if (passwordEncoder.matches(updatePasswordDto.getOldPassword(), existUser.getPassword())) {
            if (Objects.equals(updatePasswordDto.getNewPassword(), updatePasswordDto.getOldPassword())) {
                throw new IllegalArgumentException("New password cannot be the same as old password");
            }
            existUser.setPassword(passwordEncoder.encode(updatePasswordDto.getNewPassword()));
            return "Password successfully updated";
        }
        throw new IllegalArgumentException("Old password is incorrect");
    }


    @Transactional
    @Modifying
    @Override
    public String deleteUser() {
        UserEntity existUser = authenticationService.getAuthenticatedUser();
        userEntityRepository.delete(existUser);
        return "Your account has been deleted";
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        if (username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be blank");
        }
        if (userEntityRepository.findByUsername(username).isPresent()) {
            return userEntityRepository.findByUsername(username).get();
        }
        throw new IllegalArgumentException("User with username " + username + " not found");
    }


}
