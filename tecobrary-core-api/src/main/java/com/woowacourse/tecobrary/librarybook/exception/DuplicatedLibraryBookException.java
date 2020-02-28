package com.woowacourse.tecobrary.librarybook.exception;

public class DuplicatedLibraryBookException extends RuntimeException {
    
    public DuplicatedLibraryBookException(final String title) {
        super(title + " register failed");
    }
}
