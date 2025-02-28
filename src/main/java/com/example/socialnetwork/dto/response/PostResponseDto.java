package com.example.socialnetwork.dto.response;
import lombok.Data;

import java.util.List;

@Data
public class PostResponseDto {
    private Long id;
    private String content;
    private String ownerUsername;
    private Long likes;
    private List<PostCommentResponseDto> commentList;
}
