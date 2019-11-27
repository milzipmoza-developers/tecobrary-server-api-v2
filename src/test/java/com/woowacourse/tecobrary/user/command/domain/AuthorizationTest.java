package com.woowacourse.tecobrary.user.command.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthorizationTest {

    @DisplayName("none 은 user 권한을 가지지 않는다.")
    @Test
    void noneHasNotUserPermission() {
        assertFalse(Authorization.none.hasUserPermission());
    }

    @DisplayName("user 은 user 권한을 가진다.")
    @Test
    void userHasUserPermission() {
        assertTrue(Authorization.user.hasUserPermission());
    }

    @DisplayName("manager 은 user 권한을 가진다.")
    @Test
    void managerHasUserPermission() {
        assertTrue(Authorization.manager.hasUserPermission());
    }

    @DisplayName("god 은 user 권한을 가진다.")
    @Test
    void kingHasUserPermission() {
        assertTrue(Authorization.god.hasUserPermission());
    }

    @DisplayName("none 은 manager 권한을 가지지 않는다.")
    @Test
    void noneHasNotManagerPermission() {
        assertFalse(Authorization.none.hasManagerPermission());
    }

    @DisplayName("user 은 manager 권한을 가지지 않는다.")
    @Test
    void userHasNotManagerPermission() {
        assertFalse(Authorization.user.hasManagerPermission());
    }

    @DisplayName("manager 은 manager 권한을 가진다.")
    @Test
    void managerHasManagerPermission() {
        assertTrue(Authorization.manager.hasManagerPermission());
    }

    @DisplayName("god 은 manager 권한을 가진다.")
    @Test
    void kingHasManagerPermission() {
        assertTrue(Authorization.god.hasManagerPermission());
    }
}