package com.woowacourse.tecobrary.web.wishbook.exception;

import lombok.Getter;

@Getter
public class WishBookRequestUserNotFoundException extends RuntimeException {

    private final Long userId;

    public WishBookRequestUserNotFoundException(Long userId) {
        super("존재하지 않는 이용자입니다.");
        this.userId = userId;
    }
}
