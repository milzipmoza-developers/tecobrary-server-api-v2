package com.woowacourse.tecobrary.web.renthistory.exception;

public class RentReturnSerialNotFoundException extends RuntimeException {

    public static final String NOT_FOUND_MESSAGE = "존재하지 않는 시리얼 넘버입니다.";

    public RentReturnSerialNotFoundException() {
        super(NOT_FOUND_MESSAGE);
    }
}
