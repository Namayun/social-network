package com.example.socialnetwork.mapper;

import com.example.socialnetwork.dto.request.UserRequestDto;
import com.example.socialnetwork.dto.response.UserResponseDto;
import com.example.socialnetwork.dto.update.UserUpdateProfileDto;
import com.example.socialnetwork.entity.user.UserEntity;

public class UserMapper {
    public static UserEntity toEntity(UserRequestDto userRequestDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userRequestDto.getUsername().toLowerCase());
        userEntity.setPassword(userRequestDto.getPassword());
        userEntity.setEmail(userRequestDto.getEmail().toLowerCase());
        return userEntity;
    }

    public static UserResponseDto toResponse(UserEntity userEntity) {

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(userEntity.getId());
        userResponseDto.setUsername(userEntity.getUsername());
        userResponseDto.setCity(userEntity.getCity());
        userResponseDto.setCountry(userEntity.getCountry());
        userResponseDto.setFirstName(userEntity.getFirstName());
        userResponseDto.setLastName(userEntity.getLastName());
        userResponseDto.setBirthDate(userEntity.getBirthDate());
        userResponseDto.setProfilePicture(userEntity.getProfilePicture());

        return userResponseDto;

    }

    public static UserEntity toEntityUpdate(UserUpdateProfileDto userUpdateProfileDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setLastName(userUpdateProfileDto.getLastName());
        userEntity.setFirstName(userUpdateProfileDto.getFirstName());
        userEntity.setCountry(userUpdateProfileDto.getCountry());
        userEntity.setCity(userUpdateProfileDto.getCity());
        userEntity.setProfilePicture(userUpdateProfileDto.getProfilePicture());
        userEntity.setBirthDate(userUpdateProfileDto.getBirthDate());
        return userEntity;
    }

    public static void updateEntityFromDto(UserEntity userEntity, UserUpdateProfileDto dto) {
        if (dto.getFirstName() != null) {
            userEntity.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            userEntity.setLastName(dto.getLastName());
        }
        if (dto.getCountry() != null) {
            userEntity.setCountry(dto.getCountry());
        }
        if (dto.getCity() != null) {
            userEntity.setCity(dto.getCity());
        }
        if (dto.getProfilePicture() != null) {
            userEntity.setProfilePicture(dto.getProfilePicture());
        }
        if (dto.getBirthDate() != null) {
            userEntity.setBirthDate(dto.getBirthDate());
        }
    }


}
