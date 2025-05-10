package com.anirudh.portfolio.aniapp.exception.handler;

import com.anirudh.portfolio.aniapp.dto.ResponseDTO;
import com.anirudh.portfolio.aniapp.exception.ProfileNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class PortfolioExceptionHandler {
    @ExceptionHandler(value = {ProfileNotFoundException.class})
    public ResponseEntity<ResponseDTO> profileNotFoundException(ProfileNotFoundException exception) {
        return ResponseEntity.of(Optional.of(new ResponseDTO(exception.getMessage(), HttpStatus.EXPECTATION_FAILED.value())));
    }
}
