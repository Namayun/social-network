package com.example.socialnetwork.service.impl;

import com.example.socialnetwork.dto.request.PostRequestDto;
import com.example.socialnetwork.dto.response.PostResponseDto;
import com.example.socialnetwork.entity.post.PostEntity;
import com.example.socialnetwork.entity.user.UserEntity;
import com.example.socialnetwork.mapper.PostMapper;
import com.example.socialnetwork.repository.PostEntityRepository;
import com.example.socialnetwork.service.AuthenticationService;
import com.example.socialnetwork.service.PostService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostEntityRepository postEntityRepository;
    private final AuthenticationService authenticationService;
    private final UserServiceImpl userServiceImpl;


    @Transactional
    @Override
    public PostResponseDto createPost(PostRequestDto postRequestDto) {
        UserEntity existUser = authenticationService.getAuthenticatedUser();
        PostEntity newPost = PostMapper.buildNewPost(postRequestDto, existUser);
        postEntityRepository.save(newPost);
        return PostMapper.toResponse(newPost);
    }

    @Override
    public List<PostResponseDto> getPostsByUsername(String username) {
        UserEntity existUser = userServiceImpl.findUserByUsername(username);
        if (existUser.getPostList().isEmpty()) {
            throw new IllegalArgumentException("User has no posts");
        }
        return existUser.getPostList().stream().map(PostMapper::toResponse).toList();
    }

    @Override
    public String deletePost(Long id) {
        UserEntity existUser = authenticationService.getAuthenticatedUser();
        if (existUser.getPostList().stream().noneMatch(post -> post.getId().equals(id))) {
            throw new IllegalArgumentException("Post not found");
        }
        postEntityRepository.deleteById(existUser.getPostList().stream().filter(post -> post.getId().equals(id)).findFirst().get().getId());
        return "Post successfully deleted";
    }
}
