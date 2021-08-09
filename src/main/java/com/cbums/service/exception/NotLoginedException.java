package com.cbums.service.exception;

public class NotLoginedException extends Throwable {
    private final String defaultErrorMessage = "로그인 되어있지 않습니다";

    public NotLoginedException() {

    }
}
