package com.woowacourse.tecobrary.web.wishbook.exception;

import lombok.Getter;

@Getter
public class WishBookAlreadyExistException extends RuntimeException {

    private static final String DUPLICATED_WISH_BOOK_ISBN_EXCEPTION_MESSAGE = "이미 희망도서에 등록되어 있습니다.";

    private final String isbn;

    public WishBookAlreadyExistException(String isbn) {
        super(DUPLICATED_WISH_BOOK_ISBN_EXCEPTION_MESSAGE);
        this.isbn = isbn;
    }
}
