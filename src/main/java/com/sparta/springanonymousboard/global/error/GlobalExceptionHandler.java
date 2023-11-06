package com.sparta.springanonymousboard.global.error;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sparta.springanonymousboard.global.error.exception.ServerErrorException;
import com.sparta.springanonymousboard.global.error.exception.InvalidPasswordException;
import com.sparta.springanonymousboard.global.error.exception.PostNotFoundException;

import static com.sparta.springanonymousboard.global.error.ErrorCode.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PostNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handlePostNotFoundException(PostNotFoundException e) {
        final ErrorResponse response = new ErrorResponse(POST_NOT_FOUND);
        return new ResponseEntity<>(response, POST_NOT_FOUND.getHttpStatus());
    }

    @ExceptionHandler(InvalidPasswordException.class)
    protected ResponseEntity<ErrorResponse> handleInvalidPasswordException(InvalidPasswordException e) {
        final ErrorResponse response = new ErrorResponse(INVALID_PASSWORD);
        return new ResponseEntity<>(response, INVALID_PASSWORD.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        final ErrorResponse response = new ErrorResponse(INVALID_INPUT_LENGTH);
        return new ResponseEntity<>(response, INVALID_INPUT_LENGTH.getHttpStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ErrorResponse> handleServerErrorException(ServerErrorException e) {
        final ErrorResponse response = new ErrorResponse(SERVER_ERROR);
        return new ResponseEntity<>(response, SERVER_ERROR.getHttpStatus());
    }
}
