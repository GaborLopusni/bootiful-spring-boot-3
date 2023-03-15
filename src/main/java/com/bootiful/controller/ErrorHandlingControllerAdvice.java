package com.bootiful.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlingControllerAdvice {

    @ExceptionHandler
    public ProblemDetail handleIllegalStateException(IllegalStateException e) {
        var problemDetail = ProblemDetail.forStatus(HttpStatusCode.valueOf(404));
        problemDetail.setDetail("The name must start with a capital letter.");
        return problemDetail;
    }
}
