package com.woowacourse.tecobrary.user.domain;

import com.woowacourse.tecobrary.common.model.Email;
import com.woowacourse.tecobrary.common.model.HttpsUrl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    private static String TEST_GITHUB_ID = "100";
    private static UserName TEST_USER_NAME = new UserName("thedevluffy");
    private static Email TEST_USER_EMAIL = new Email("thedevluffy@gmail.com");
    private static HttpsUrl TEST_HTTPS_URL = new HttpsUrl("https://avatars0.githubusercontent.com/u/52121827?s=460&v=4");
    private static UserGithubInfo TEST_USER_GITHUB_INFO = new UserGithubInfo(TEST_GITHUB_ID, TEST_USER_NAME, TEST_USER_EMAIL, TEST_HTTPS_URL);
    private static UserAuthorization TEST_USER_AUTHORIZATION = new UserAuthorization(Authorization.NONE);

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