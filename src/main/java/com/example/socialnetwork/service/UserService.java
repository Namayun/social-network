package com.example.socialnetwork.service;




import com.example.socialnetwork.dto.response.UserResponseDto;
import com.example.socialnetwork.dto.update.UpdatePasswordDto;
import com.example.socialnetwork.dto.update.UserUpdateProfileDto;
import com.example.socialnetwork.entity.user.UserEntity;


public interface UserService {
    UserResponseDto updateProfile(UserUpdateProfileDto userUpdateProfileDto);
    String updatePassword(UpdatePasswordDto updatePasswordDto);
    String deleteUser();
    UserEntity findUserByUsername(String username);



}
