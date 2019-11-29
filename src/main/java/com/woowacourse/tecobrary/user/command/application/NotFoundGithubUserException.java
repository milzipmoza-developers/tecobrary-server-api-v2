/*
 * @(#) NotFoundGithubUserException.java
 *
 * v 1.0.0
 *
 * 2019.11.29
 *
 * Copyright (c) 2019 woowacourse, thedevluffy
 * All rights reserved
 */

package com.woowacourse.tecobrary.user.command.application;

public class NotFoundGithubUserException extends RuntimeException {

    public NotFoundGithubUserException() {
        super("존재 하지 않는 유저");
    }
}
