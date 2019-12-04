package com.woowacourse.tecobrary.wishbook.command.domain;

public class DuplicatedWishBookIsbnException extends RuntimeException {

    public static final String DUPLICATED_WISH_BOOK_ISBN_EXCEPTION_MESSAGE = "이미 희망도서에 등록되어 있습니다.";

    public DuplicatedWishBookIsbnException() {
        super(DUPLICATED_WISH_BOOK_ISBN_EXCEPTION_MESSAGE);
    }
}
