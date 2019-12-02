package com.woowacourse.tecobrary.librarybook.exception;

public class NotFoundLibraryBookException extends RuntimeException {

    public static final String NOT_FOUND_LIBRARY_BOOK_EXCEPTION_MESSAGE = "존재 하지 않는 도서";

    public NotFoundLibraryBookException() {
        super(NOT_FOUND_LIBRARY_BOOK_EXCEPTION_MESSAGE);
    }
}
