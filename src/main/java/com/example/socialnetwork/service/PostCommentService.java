package com.example.socialnetwork.service;

import com.example.socialnetwork.dto.response.PostResponseDto;

public interface PostCommentService {
    PostResponseDto addComment(Long postId, String username, String comment);
    PostResponseDto deleteComment(Long postId, Long commentId);
}
