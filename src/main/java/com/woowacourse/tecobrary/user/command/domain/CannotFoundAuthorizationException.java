/*
 * @(#) CannotFoundAuthorizationException.java
 *
 * v 1.0.0
 *
 * 2019.11.29
 *
 * Copyright (c) 2019 woowacourse, thedevluffy
 * All rights reserved
 */

package com.woowacourse.tecobrary.user.command.domain;

public class CannotFoundAuthorizationException extends RuntimeException {

    public CannotFoundAuthorizationException() {
        super("존재하지 않는 Authorization 입니다.");
    }
}
