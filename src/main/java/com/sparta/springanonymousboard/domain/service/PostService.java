package com.sparta.springanonymousboard.domain.service;

import com.sparta.springanonymousboard.domain.dto.request.PostCreateRequestDto;
import com.sparta.springanonymousboard.domain.dto.request.PostEditRequestDto;
import com.sparta.springanonymousboard.domain.dto.response.PostCreateResponseDto;
import com.sparta.springanonymousboard.domain.dto.response.PostDetailResponseDto;
import com.sparta.springanonymousboard.domain.dto.response.PostPreviewResponseDto;
import com.sparta.springanonymousboard.domain.entity.Post;
import com.sparta.springanonymousboard.domain.repository.PostRepository;
import com.sparta.springanonymousboard.global.error.exception.InvalidPasswordException;
import com.sparta.springanonymousboard.global.error.exception.PostNotFoundException;
import com.sparta.springanonymousboard.global.security.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;




@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PasswordEncoder passwordEncoder;

    public List<PostPreviewResponseDto> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(PostPreviewResponseDto::new)
                .toList();
    }

    public PostDetailResponseDto getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);

        return new PostDetailResponseDto(post);
    }

    @Transactional
    public PostCreateResponseDto createPost(PostCreateRequestDto request) {
        passwordEncoding(request);

        Post createdPost = Post.create(request); // Post 엔티티의 자체 메서드를 이용해서 생성
        Post savedPost = postRepository.save(createdPost);

        return new PostCreateResponseDto(savedPost.getId());
    }

    private void passwordEncoding(PostCreateRequestDto request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        request.changeEncodedPassword(encodedPassword);
    }

    @Transactional
    public PostDetailResponseDto editPost(Long postId, PostEditRequestDto request, String password) {
        Post post = getValidatedPost(postId, password);
        post.edit(request); // Post 엔티티의 자체 수정 메서드를 이용해서 수정

        return new PostDetailResponseDto(post);
    }

    @Transactional
    public void delete(Long postId, String password) {
        Post post = getValidatedPost(postId, password);

        postRepository.delete(post);
    }

    private Post getValidatedPost(Long postId, String password) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        validatePassword(post, password);

        return post;
    }

    private void validatePassword(Post post, String password) {
        String encodedPassword = passwordEncoder.encode(password);

        if (!post.getPassword().equals(encodedPassword)) {
            throw new InvalidPasswordException();
        }
    }
}
