package com.woowacourse.tecobrary.common.exception;

public class MalformedEmailException extends RuntimeException {

    private static final String MALFORMED_EMAIL_MESSAGE = "올바른 이메일 형식이 아닙니다.";

    public MalformedEmailException() {
        super(MALFORMED_EMAIL_MESSAGE);
    }
}
