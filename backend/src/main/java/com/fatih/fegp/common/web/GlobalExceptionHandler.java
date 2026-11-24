package com.fatih.fegp.common.web;

import com.fatih.fegp.common.error.domain.BusinessRuleException;
import com.fatih.fegp.common.error.domain.ResourceNotFoundException;
import com.fatih.fegp.common.error.infrastructure.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private String resolvePath(HttpServletRequest request) {
        if (request == null || request.getRequestURI() == null) {
            return "UNDEFINED";
        }
        return request.getRequestURI();
    }

    private ApiErrorResponse buildResponse(BusinessRuleException exception, HttpStatus status, HttpServletRequest request) {
        return new ApiErrorResponse(
                exception.getCode(),
                exception.getMessage(),
                status.value(),
                resolvePath(request),
                Instant.now()
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleNotFound(ResourceNotFoundException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiErrorResponse body = buildResponse(exception, status, request);
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ApiErrorResponse> handleBusinessRule(BusinessRuleException exception, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_CONTENT;
        ApiErrorResponse body = buildResponse(exception, status, request);
        return ResponseEntity.status(status).body(body);
    }


}
