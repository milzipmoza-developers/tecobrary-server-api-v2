package com.woowacourse.tecobrary.web.renthistory.exception;

public class RentReturnUserNotFoundException extends RuntimeException {

    public static final String NOT_FOUND_MESSAGE = "존재하지 않는 유저입니다.";

    public RentReturnUserNotFoundException() {
        super(NOT_FOUND_MESSAGE);
    }
}
