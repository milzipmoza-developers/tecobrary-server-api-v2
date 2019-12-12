package com.woowacourse.tecobrary.renthistory.domain;

public class NotPermittedUserException extends RuntimeException {

    public static final String NOT_PERMITTED_USER_EXCEPTION_MESSAGE = "반납을 처리할 수 없는 유저입니다.";

    public NotPermittedUserException() {
        super(NOT_PERMITTED_USER_EXCEPTION_MESSAGE);
    }
}
