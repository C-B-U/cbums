package com.cbums.common.exceptions;

public class AccessException extends BusinessException {
    public AccessException(ErrorCode errorCode) {
        super(errorCode);
    }

}
