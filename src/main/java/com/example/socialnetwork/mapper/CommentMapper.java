package com.example.socialnetwork.mapper;

import com.example.socialnetwork.dto.response.PostCommentResponseDto;
import com.example.socialnetwork.entity.comment.PostCommentEntity;

public class CommentMapper {
    public static PostCommentResponseDto mapToPostCommentResponseDto(PostCommentEntity comment) {
        PostCommentResponseDto commentResponseDto = new PostCommentResponseDto();
        commentResponseDto.setId(comment.getId());
        commentResponseDto.setContent(comment.getText());
        commentResponseDto.setOwnerUsername(comment.getAuthor().getUsername());
        commentResponseDto.setCreatedAt(comment.getCreatedAt());
        return commentResponseDto;
    }
}
