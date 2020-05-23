package com.woowacourse.tecobrary.admin.web.wishbook.exception;

import lombok.Getter;

@Getter
public class WishBookNotFoundException extends RuntimeException {

    private final Long id;

    public WishBookNotFoundException(Long id) {
        super("희망도서를 찾을 수 없습니다.");
        this.id = id;
    }
}
