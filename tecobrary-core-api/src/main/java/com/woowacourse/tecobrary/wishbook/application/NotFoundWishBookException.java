package com.woowacourse.tecobrary.wishbook.application;

public class NotFoundWishBookException extends RuntimeException {

    private static final String NOT_FOUND_WISH_BOOK_EXCEPTION_MESSAGE = "존재하지 않는 희망도서 입니다.";

    public NotFoundWishBookException() {
        super(NOT_FOUND_WISH_BOOK_EXCEPTION_MESSAGE);
    }
}
