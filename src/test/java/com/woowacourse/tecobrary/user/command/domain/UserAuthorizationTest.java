package com.woowacourse.tecobrary.user.command.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserAuthorizationTest {

    @DisplayName("성공적으로 Authorization 을 변경한다.")
    @Test
    void updateAuthorization() {
        UserAuthorization authorization = new UserAuthorization(Authorization.none);
        authorization.updateAuthorization("god");
        assertEquals(authorization.getAuthorization(), Authorization.god);
    }

    @DisplayName("Authorization 업데이트에 실패한다. 존재하지 않는 authorization")
    @Test
    void updateAuthorizationFailedDoesNotExist() {
        UserAuthorization authorization = new UserAuthorization(Authorization.none);
        assertThrows(CannotFoundAuthorizationException.class, () ->
                authorization.updateAuthorization("NOHERE"));

    }
}