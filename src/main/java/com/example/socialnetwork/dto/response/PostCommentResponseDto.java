package com.example.socialnetwork.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostCommentResponseDto {
    private Long id;
    private String content;
    private String ownerUsername;
    private LocalDateTime createdAt;

}
