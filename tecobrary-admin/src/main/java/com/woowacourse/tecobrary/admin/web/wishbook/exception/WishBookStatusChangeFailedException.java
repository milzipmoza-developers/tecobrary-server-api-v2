package com.woowacourse.tecobrary.admin.web.wishbook.exception;

import lombok.Getter;

@Getter
public class WishBookStatusChangeFailedException extends RuntimeException {

    private final Long id;

    public WishBookStatusChangeFailedException(Long id) {
        super("지원하지 않는 상태입니다.");
        this.id = id;
    }
}
