package com.woowacourse.tecobrary.user.command.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserAuthorizationTest {

    @DisplayName("성공적으로 Authorization 을 변경한다.")
    @Test
    void updateAuthorization() {
        UserAuthorization authorization = new UserAuthorization(Authorization.NONE);
        authorization.updateAuthorization("KING");
        assertEquals(authorization.getAuthorization(), Authorization.KING);
    }

    @DisplayName("Authorization 업데이트에 실패한다. 존재하지 않는 authorization")
    @Test
    void updateAuthorizationFailedDoesNotExist() {
        UserAuthorization authorization = new UserAuthorization(Authorization.NONE);
        assertThrows(IllegalArgumentException.class, () -> authorization.updateAuthorization("NOHERE"));

    }
}