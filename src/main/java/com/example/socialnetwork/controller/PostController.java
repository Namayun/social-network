package com.example.socialnetwork.controller;

import com.example.socialnetwork.dto.request.PostRequestDto;
import com.example.socialnetwork.dto.response.PostResponseDto;
import com.example.socialnetwork.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;


    @PostMapping("/create")
    public ResponseEntity<PostResponseDto> createPost(
            @Valid @RequestBody PostRequestDto postRequestDto) {
        return ResponseEntity.ok(postService.createPost(postRequestDto));
    }

    @GetMapping("/find-posts")
    public ResponseEntity<List<PostResponseDto>> getPostsByUsername(
            @RequestParam String username) {
        return ResponseEntity.ok(postService.getPostsByUsername(username));
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deletePost(
            @RequestParam Long id
    ) {
        return ResponseEntity.ok(postService.deletePost(id));
    }

}
