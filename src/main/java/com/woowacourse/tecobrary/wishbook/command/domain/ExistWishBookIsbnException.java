package com.woowacourse.tecobrary.wishbook.command.domain;

public class ExistWishBookIsbnException extends RuntimeException {

    private static final String EXIST_WISH_BOOK_ISBN_EXCEPTION_MESSAGE = "WishBook 에 등록된 Isbn 입니다.";

    public ExistWishBookIsbnException() {
        super(EXIST_WISH_BOOK_ISBN_EXCEPTION_MESSAGE);
    }
}
