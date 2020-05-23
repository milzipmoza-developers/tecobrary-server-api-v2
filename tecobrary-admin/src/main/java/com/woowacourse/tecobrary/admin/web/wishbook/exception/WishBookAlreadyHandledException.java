package com.woowacourse.tecobrary.admin.web.wishbook.exception;

import lombok.Getter;

@Getter
public class WishBookAlreadyHandledException extends RuntimeException {

    private final String title;

    public WishBookAlreadyHandledException(String title) {
        super("이미 처리된 희망도서 입니다.");
        this.title = title;
    }
}
