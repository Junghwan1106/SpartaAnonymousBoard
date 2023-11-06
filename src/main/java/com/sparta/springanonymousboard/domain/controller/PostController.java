package com.sparta.springanonymousboard.domain.controller;

import com.sparta.springanonymousboard.domain.dto.request.PostCreateRequestDto;
import com.sparta.springanonymousboard.domain.dto.request.PostEditRequestDto;
import com.sparta.springanonymousboard.domain.dto.response.PostCreateResponseDto;
import com.sparta.springanonymousboard.domain.dto.response.PostDetailResponseDto;
import com.sparta.springanonymousboard.domain.dto.response.PostPreviewResponseDto;
import com.sparta.springanonymousboard.domain.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;
    @GetMapping("/posts")
    public ResponseEntity<List<PostPreviewResponseDto>> getPosts() {
        List<PostPreviewResponseDto> posts = postService.getPosts();

        return ResponseEntity.ok(posts);
    }
    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDetailResponseDto> getPost(@PathVariable Long postId) {
        PostDetailResponseDto postDetail = postService.getPost(postId);

        return ResponseEntity.ok(postDetail);
    }
    @PostMapping("/posts")
    public ResponseEntity<PostCreateResponseDto> createPost(@Valid @RequestBody PostCreateRequestDto request) {
        PostCreateResponseDto createPostDto = postService.createPost(request);

        return ResponseEntity.ok(createPostDto);
    }
    @PatchMapping("/posts/{postId}")
    public ResponseEntity<PostDetailResponseDto> editPost(
            @PathVariable Long postId,
            @Valid @RequestBody PostEditRequestDto request,
            @RequestHeader String password) {

        PostDetailResponseDto editedPostDetail = postService.editPost(postId, request, password);

        return ResponseEntity.ok(editedPostDetail);
    }
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId, @RequestHeader("password") String password) {
        postService.delete(postId, password);

        return ResponseEntity.noContent().build();
    }
}
