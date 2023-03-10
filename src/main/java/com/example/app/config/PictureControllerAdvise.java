package com.example.app.config;

import com.example.app.dto.ErrorResponse;
import com.example.app.exception.IncorrectPictureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PictureControllerAdvise {
    @ExceptionHandler
    public ResponseEntity<?> handleIncorrectPicture(IncorrectPictureException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage()));
    }
}
