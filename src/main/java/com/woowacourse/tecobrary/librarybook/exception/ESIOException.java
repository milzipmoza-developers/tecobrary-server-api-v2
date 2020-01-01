package com.woowacourse.tecobrary.librarybook.exception;

public class ESIOException extends RuntimeException {

    private static final String ES_IO_EXCEPTION_MESSAGE = "엘라스틱 서치 문제 발생";

    public ESIOException(final Throwable cause) {
        super(ES_IO_EXCEPTION_MESSAGE, cause);
    }
}
