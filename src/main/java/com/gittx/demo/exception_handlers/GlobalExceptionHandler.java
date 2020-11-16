package com.gittx.demo.exception_handlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

//@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> resourceNotFound(ResourceNotFoundException ex) {
        ExceptionResponseDto response = new ExceptionResponseDto();
        response.setMessage("NOT_FOUND");
        response.setExceptionMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponseDto>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExists.class)
    public ResponseEntity<ExceptionResponseDto> resourceAlreadyExists(ResourceAlreadyExists ex) {
        ExceptionResponseDto response=new ExceptionResponseDto();
        response.setMessage("CONFLICT");
        response.setExceptionMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponseDto>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponseDto> customException(CustomException ex) {
        ExceptionResponseDto response=new ExceptionResponseDto();
        response.setMessage("BAD_REQUEST");
        response.setExceptionMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponseDto>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionResponseDto> unauthorizedException(UnauthorizedException ex) {
        ExceptionResponseDto response=new ExceptionResponseDto();
        response.setMessage("UNAUTHORIZED");
        response.setExceptionMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<ExceptionResponseDto>(response, HttpStatus.UNAUTHORIZED);
    }

}
