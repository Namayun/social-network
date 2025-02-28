package com.example.socialnetwork.mapper;

import com.example.socialnetwork.dto.request.PostRequestDto;
import com.example.socialnetwork.dto.response.PostResponseDto;
import com.example.socialnetwork.entity.post.PostEntity;
import com.example.socialnetwork.entity.user.UserEntity;

public class PostMapper {
    public static PostEntity buildNewPost(PostRequestDto postRequestDto, UserEntity existUser) {
        PostEntity postEntity = new PostEntity();
        postEntity.setContent(postRequestDto.getContent());
        postEntity.setOwner(existUser);
        return postEntity;
    }

    public static PostResponseDto toResponse(PostEntity postEntity) {
        PostResponseDto postResponseDto = new PostResponseDto();
        postResponseDto.setContent(postEntity.getContent());
        postResponseDto.setId(postEntity.getId());
        postResponseDto.setLikes(postEntity.getLikes());
        postResponseDto.setOwnerUsername(postEntity.getOwner().getUsername());
        postResponseDto.setCommentList(postEntity.getCommentList().stream().map(CommentMapper::mapToPostCommentResponseDto).toList());
        return postResponseDto;
    }



}
