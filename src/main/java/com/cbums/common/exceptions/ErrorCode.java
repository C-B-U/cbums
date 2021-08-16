package com.cbums.common.exceptions;

public enum ErrorCode {
    DUPLICATED_EMAIL(400, "AU_001", "이미 존재하는 email입니다."),
    DUPLICATED_CLASS_NUMBER(400, "AU_002", "이미 존재하는 학번입니다."),
    DUPLICATED_NICK_NAME(400, "AU_003", "이미 존재하는 닉네임입니다."),
    BAD_LOGIN(400, "AU_004", "잘못된 아이디 또는 패스워드입니다.");


    private final String code;
    private final String message;
    private final int status;

    ErrorCode(int status, String code, String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }
}
