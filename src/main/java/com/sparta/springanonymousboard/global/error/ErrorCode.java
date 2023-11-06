package com.sparta.springanonymousboard.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum ErrorCode {

    POST_NOT_FOUND(NOT_FOUND, "게시글이 존재하지 않습니당."),
    INVALID_INPUT_LENGTH(BAD_REQUEST, "입력 길이가 잘못되었습니다."),
    INVALID_PASSWORD(UNAUTHORIZED, "비밀번호가 잘못되었습니다."),
    SERVER_ERROR(INTERNAL_SERVER_ERROR, "서버에 오류가 발생했습니다.");

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
