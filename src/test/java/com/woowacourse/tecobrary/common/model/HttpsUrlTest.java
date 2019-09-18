package com.woowacourse.tecobrary.common.model;

import com.woowacourse.tecobrary.common.exception.MalformedHttpsUrlException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HttpsUrlTest {

    @DisplayName("성공적으로 HttpsUrl 객체를 생성한다.")
    @Test
    void constructSuccess() {
        assertDoesNotThrow(() -> new HttpsUrl("https://github.com"));
    }

    @DisplayName("HttpsUrl 객체 생성에 실패한다. HttpsUrl 형식 오류")
    @Test
    void constructThrowException() {
        assertThrows(MalformedHttpsUrlException.class, () -> new HttpsUrl("안녕하세요."));
    }
}