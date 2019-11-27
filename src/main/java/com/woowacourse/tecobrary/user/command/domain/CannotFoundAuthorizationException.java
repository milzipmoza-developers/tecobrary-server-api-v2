package com.woowacourse.tecobrary.user.command.domain;

public class CannotFoundAuthorizationException extends RuntimeException {
    public CannotFoundAuthorizationException() {
        super("존재하지 않는 Authorization 입니다.");
    }
}
