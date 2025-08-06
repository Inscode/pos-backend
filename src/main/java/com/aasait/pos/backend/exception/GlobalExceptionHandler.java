package com.aasait.pos.backend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        log.error("Unhandled exception: ", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ErrorResponse(
                        LocalDateTime.now(),
                        "Internal server error",
                        ex.getMessage(),
                        500
                )
        );
    }

    @Data
    @AllArgsConstructor
    public class ErrorResponse {
        private LocalDateTime timestamp;
        private String message;
        private String details;
        private int status;
    }

    // Add other specific handlers if needed
}
