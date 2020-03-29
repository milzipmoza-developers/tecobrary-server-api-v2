/*
 * @(#) UserNameLengthException.java
 *
 * v 1.0.0
 *
 * 2019.11.29
 *
 * Copyright (c) 2019 woowacourse, thedevluffy
 * All rights reserved
 */

package com.woowacourse.tecobrary.user.entity;

class UserNameLengthException extends RuntimeException {

    UserNameLengthException() {
        super("UserName 은 255자를 넘을 수 없습니다.");
    }
}
