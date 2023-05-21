package com.example.example.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalAdvice extends Exception {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception e) {
        log.trace("e : {}", e);
        log.trace("e.getLocalizedMessage : {}", e.getLocalizedMessage());
        log.trace("e.getClass : {}", e.getClass());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("");
    }


}
