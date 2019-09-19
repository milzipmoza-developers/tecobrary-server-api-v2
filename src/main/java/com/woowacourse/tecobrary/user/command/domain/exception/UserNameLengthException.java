package com.woowacourse.tecobrary.user.command.domain.exception;

public class UserNameLengthException extends RuntimeException {
    public UserNameLengthException() {
        super("UserName 은 255자를 넘을 수 없습니다.");
    }
}
