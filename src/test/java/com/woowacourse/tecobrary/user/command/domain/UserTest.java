package com.woowacourse.tecobrary.user.command.domain;

import com.woowacourse.tecobrary.user.common.UserStatics;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest implements UserStatics {

    private static final String UPDATED_NAME = "새로운 이름";
    private static final String UPDATED_AUTHORIZATION = "KING";

    @DisplayName("성공적으로 User 도메인 객체를 생성한다.")
    @Test
    void constructor() {
        assertDoesNotThrow(() -> new User(TEST_USER_GITHUB_INFO, TEST_USER_AUTHORIZATION));
    }

    @DisplayName("성공적으로 UserName 을 수정한다.")
    @Test
    void updateUserName() {
        User user = new User(TEST_USER_GITHUB_INFO, TEST_USER_AUTHORIZATION);
        assertEquals(
                UPDATED_NAME,
                user.updateUserName(UPDATED_NAME)
        );
    }

    @DisplayName("성공적으로 UserAuthorization 을 수정한다.")
    @Test
    void updateUserAuthorization() {
        User user = new User(TEST_USER_GITHUB_INFO, TEST_USER_AUTHORIZATION);
        assertEquals(
                UPDATED_AUTHORIZATION,
                user.updateAuthorization("KING")
        );
    }
}