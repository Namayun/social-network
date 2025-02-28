package com.example.socialnetwork.service;

import com.example.socialnetwork.dto.request.PostRequestDto;
import com.example.socialnetwork.dto.response.PostResponseDto;

import java.util.List;

public interface PostService {
    PostResponseDto createPost(PostRequestDto postRequestDto);

    List<PostResponseDto> getPostsByUsername(String username);

    String deletePost(Long id);


}
