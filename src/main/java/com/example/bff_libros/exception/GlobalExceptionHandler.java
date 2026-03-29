package com.example.bff_libros.exception;

import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {    
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<?> handleFeignException(FeignException ex) {

        String body = ex.contentUTF8();

        return ResponseEntity
                .status(ex.status())
                .body(new ErrorResponse(body));
    }
   
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception ex) {

        return ResponseEntity
                .status(500)
                .body(new ErrorResponse("Error interno en el BFF"));
    }
}