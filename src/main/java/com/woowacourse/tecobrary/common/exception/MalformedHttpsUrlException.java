package com.woowacourse.tecobrary.common.exception;

public class MalformedHttpsUrlException extends RuntimeException {

    private static final String MALFORMED_HTTPS_URL_MESSAGE = "유효한 HttpsUrl Format 이 입니다. https:// 로 시작해야 합니다.";

    public MalformedHttpsUrlException() {
        super(MALFORMED_HTTPS_URL_MESSAGE);
    }
}
