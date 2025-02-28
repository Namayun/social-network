package com.example.socialnetwork.dto.response;


import com.example.socialnetwork.entity.photo.PictureEntity;


import lombok.Data;

import java.time.LocalDate;


@Data
public class UserResponseDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private PictureEntity profilePicture;
    private LocalDate birthDate;
    private String country;
    private String city;
}
