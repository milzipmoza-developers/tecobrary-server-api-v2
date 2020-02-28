package com.woowacourse.tecobrary.wishbook.command.application;

public class AlreadySoftDeletedWishBookException extends RuntimeException {

    public static final String NOT_EXIST_SOFT_DELETED_WISH_BOOK_EXCEPTION_MESSAGE = "해당하는 희망도서는 존재하지 않거나 이미 처리 되었습니다.";

    public AlreadySoftDeletedWishBookException() {
        super(NOT_EXIST_SOFT_DELETED_WISH_BOOK_EXCEPTION_MESSAGE);
    }
}
