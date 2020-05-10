package com.woowacourse.tecobrary.web.user.exception;

public class UserNotFoundException extends RuntimeException {

    public static final String NOT_FOUND_USER_MESSAGE = "해당 유저를 찾을 수 없습니다.";

    public UserNotFoundException() {
        super(NOT_FOUND_USER_MESSAGE);
    }
}
