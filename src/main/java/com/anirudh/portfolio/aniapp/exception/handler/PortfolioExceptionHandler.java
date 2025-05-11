package com.anirudh.portfolio.aniapp.exception.handler;

import com.anirudh.portfolio.aniapp.dto.ErrorResponse;
import com.anirudh.portfolio.aniapp.exception.InvalidResumeException;
import com.anirudh.portfolio.aniapp.exception.ProfileNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;

@ControllerAdvice
public class PortfolioExceptionHandler {
    @ExceptionHandler(value = {ProfileNotFoundException.class})
    public ResponseEntity<ErrorResponse> profileNotFoundException(ProfileNotFoundException exception) {
        return ResponseEntity.of(Optional.of(ErrorResponse.builder()
                .message("Error occurred")
                .details(List.of(exception.getMessage()))
                .status(HttpStatus.NOT_FOUND.value()).build()));
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<ErrorResponse> nullPointerEx(NullPointerException exception) {
        return ResponseEntity.of(Optional.of(ErrorResponse.builder()
                .message("NPE occurred")
                .details(List.of(exception.getMessage()))
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build()));
    }

    @ExceptionHandler(value = {InvalidResumeException.class})
    public ResponseEntity<ErrorResponse> resumeEx(InvalidResumeException exception) {
        return ResponseEntity.of(Optional.of(ErrorResponse.builder()
                .message("Resume Not Valid")
                .details(List.of(exception.getMessage()))
                .status(HttpStatus.BAD_REQUEST.value()).build()));
    }
}
