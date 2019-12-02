package com.woowacourse.tecobrary.user.command.application;

public class NotFoundUserException extends RuntimeException {

    public static final String NOT_FOUND_USER_EXCEPTION_MESSAGE = "존재하지 않는 유저입니다.";

    public NotFoundUserException() {
        super(NOT_FOUND_USER_EXCEPTION_MESSAGE);
    }
}
