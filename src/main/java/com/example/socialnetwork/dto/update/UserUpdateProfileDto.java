package com.example.socialnetwork.dto.update;


import com.example.socialnetwork.entity.photo.PictureEntity;
import lombok.Data;

import java.time.LocalDate;


@Data
public class UserUpdateProfileDto {
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private LocalDate birthDate;
    private PictureEntity profilePicture;
}
