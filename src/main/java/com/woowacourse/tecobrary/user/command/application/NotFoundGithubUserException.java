package com.woowacourse.tecobrary.user.command.application;

public class NotFoundGithubUserException extends RuntimeException {
    public NotFoundGithubUserException() {
        super("존재 하지 않는 유저");
    }
}
