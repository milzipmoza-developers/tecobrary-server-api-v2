package com.woowacourse.tecobrary.wishbook.command.domain;

public class NotFoundWishBookException extends RuntimeException {

    private static final String NOT_FOUND_WISH_BOOK_EXCEPTION_MESSAGE = "해당하는 WishBook을 찾지 못했습니다.";

    public NotFoundWishBookException() {
        super(NOT_FOUND_WISH_BOOK_EXCEPTION_MESSAGE);
    }
}
