package com.cbums.common.exceptions;

public enum ErrorCode {
    DUPLICATED_EMAIL(400, "AU_001", "이미 존재하는 email입니다."),
    DUPLICATED_CLASS_NUMBER(400, "AU_002", "이미 존재하는 학번입니다."),
    DUPLICATED_NICK_NAME(400, "AU_003", "이미 존재하는 닉네임입니다."),
    BAD_LOGIN(400, "AU_004", "잘못된 아이디 또는 패스워드입니다."),
    NOT_ADMISSION(400, "AU_005", "가입이 승인되지 않았습니다"),
    ALREADY_CHECK_ADMISSION(400, "AU_006", "이미 가입승인을 받았습니다."),
    NOT_MATCH_AUTHCODE(400, "AU_007", "인증코드가 일치하지 않습니다"),

    USER_UNAUTHORIZED(400,"ACC_001","접근 권한이 없습니다"),
    USER_BAD_ACCESS(400, "ACC_002", "접근 허용된 사용자가 아닙니다"),

    ALREADY_DONE(400, "ACT_001", "이미 수행한 동작입니다"),

    NOT_FOUNDED_ID(500, "ER_001", "id에 해당하는 값을 찾지 못했습니다.");


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
