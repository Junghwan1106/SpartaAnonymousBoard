package com.sparta.springanonymousboard.domain.dto.request;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PostCreateRequestDto {

    @NotBlank
    @Length(min = 1, max = 30)
    private String title;

    @NotBlank
    @Length(min = 2, max = 20)
    private String author;

    @NotBlank
    @Length(min = 4, max = 50)
    private String password;

    @NotBlank
    private String contents;

    public void changeEncodedPassword(String encodedPassword) {
        this.password = encodedPassword;
    }
}