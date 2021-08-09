package com.cbums.controller;

import com.cbums.service.exception.ListEmptyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NullPointerException.class)
    public String getNullPointerExceptionPage(NullPointerException e) {
        return e.getMessage();
    }

    @ExceptionHandler(ListEmptyException.class)
    public String getListEmptyExceptionPage(ListEmptyException e) {
        return e.getMessage();
    }
}
