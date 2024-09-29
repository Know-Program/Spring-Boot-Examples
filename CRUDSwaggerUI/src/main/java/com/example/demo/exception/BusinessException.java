package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private HttpStatus errorCode;
    private String errorMessage;

    public BusinessException(String errorMessage, HttpStatus errorCode) {
        super(errorMessage); // pass message to RuntimeException()
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
