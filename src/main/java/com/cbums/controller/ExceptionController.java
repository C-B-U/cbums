package com.cbums.controller;

import com.cbums.service.exception.NotAcceptMemberException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.mail.MessagingException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(NullPointerException.class)
    public String getNullPointerExceptionPage(NullPointerException e) {
        return e.getMessage();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String getListEmptyExceptionPage(NoSuchElementException e) {
        return e.getMessage();
    }

    @ExceptionHandler(NotAcceptMemberException.class)
    public String getLNotAcceptMemberExceptionPage(NotAcceptMemberException e) {
        return e.getMessage();
    }

    @ExceptionHandler(MessagingException.class)
    public String getMessagingExceptionPage(MessagingException e) {
        return e.getMessage();
    }
}
