package com.woowacourse.tecobrary.serial.exception;

public class UniqueConstraintException extends RuntimeException {

    public static final String UNIQUE_CONSTRAINT_EXCEPTION_MESSAGE = "해당하는 일련번호가 이미 존재합니다.";

    public UniqueConstraintException() {
        super(UNIQUE_CONSTRAINT_EXCEPTION_MESSAGE);
    }
}
