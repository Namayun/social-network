package com.example.socialnetwork.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostRequestDto {
    @NotNull
    @Size(max = 5000, message = "Пост должен содержать не больше 5000 символов")
    private String content;
}
