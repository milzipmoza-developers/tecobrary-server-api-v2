package com.woowacourse.tecobrary.domain.wishbook.entity;

import java.util.EnumSet;

public enum WishBookStatus {
    REQUESTED, // 요청등록
    ENROLLED, // LibraryBook 등록
    CANCELED, // 취소
    ;

    private static final EnumSet<WishBookStatus> HANDLED_WISH_BOOK = EnumSet.of(
            ENROLLED, CANCELED
    );

    public boolean isHandled() {
        return HANDLED_WISH_BOOK.contains(this);
    }
}
