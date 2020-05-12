package com.woowacourse.tecobrary.web.librarybook.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LibraryBookNotFoundException extends RuntimeException {

    public static final String NOT_FOUND_LIBRARY_BOOK_EXCEPTION_MESSAGE = "존재 하지 않는 도서";

    public LibraryBookNotFoundException(Long id) {
        super(NOT_FOUND_LIBRARY_BOOK_EXCEPTION_MESSAGE);
        log.warn("[LibraryBookNotFoundException] id={}", id);
    }
}
