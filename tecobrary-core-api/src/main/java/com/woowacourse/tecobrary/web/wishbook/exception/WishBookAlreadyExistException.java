package com.woowacourse.tecobrary.web.wishbook.exception;

import lombok.Getter;

@Getter
public class WishBookAlreadyExistException extends RuntimeException {

    private final String isbn;

    public WishBookAlreadyExistException(String isbn, String message) {
        super(message);
        this.isbn = isbn;
    }
}
