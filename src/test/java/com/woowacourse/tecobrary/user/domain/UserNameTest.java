package com.woowacourse.tecobrary.user.domain;

import com.woowacourse.tecobrary.user.domain.exception.UserNameLengthException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserNameTest {

    @DisplayName("성공적으로 UserName 객체를 생성한다.")
    @Test
    void constructSuccess() {
        assertDoesNotThrow(() -> new UserName("권민철"));
    }

    @DisplayName("UserName 객체 생성에 실패한다. 글자수 초과")
    @Test
    void constructFailedNameLength() {
        String upper255Letter = IntStream.rangeClosed(0, 255)
                .mapToObj(i -> "a")
                .collect(Collectors.joining());

        assertThrows(UserNameLengthException.class, () -> new UserName(upper255Letter));
    }
}