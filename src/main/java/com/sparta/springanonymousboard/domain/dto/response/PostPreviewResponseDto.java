package com.sparta.springanonymousboard.domain.dto.response;

import com.sparta.springanonymousboard.domain.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostPreviewResponseDto {

    private final Long id;
    private final String title;
    private final String author;
    private final LocalDateTime createdAt;

    public PostPreviewResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.author = post.getAuthor();
        this.createdAt = post.getCreatedAt();
    }
}