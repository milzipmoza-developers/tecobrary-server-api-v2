package com.woowacourse.tecobrary.user.ui;

import com.woowacourse.tecobrary.common.util.RestAssuredTestUtils;
import com.woowacourse.tecobrary.user.command.application.UserGithubService;
import com.woowacourse.tecobrary.user.command.application.api.GithubApiService;
import com.woowacourse.tecobrary.user.common.UserStatic;
import com.woowacourse.tecobrary.user.infra.util.JwtUtils;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;

class GithubUserApiControllerTest extends RestAssuredTestUtils implements UserStatic {

    private static final String CODE_FOR_TEST = "test_code";
    private static final String ACCESS_TOKEN_FOR_TEST = "test_access_token";

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private GithubApiService githubApiService;

    @Mock
    private UserGithubService userGithubService;

    @BeforeEach
    void initialiseRestAssuredMockMvcStandalone() {
        RestAssuredMockMvc.standaloneSetup(new GithubUserApiController(jwtUtils, githubApiService, userGithubService));
    }

    @DisplayName("[GET] /github/user/, data.sql 로 미리 저장하는 user 에 대한 json 이 성공적으로 반환된다.")
    @Test
    void getGithubUserInformationWithSavedUser() {
        when(githubApiService.getGithubAccessToken(CODE_FOR_TEST)).thenReturn(ACCESS_TOKEN_FOR_TEST);
        when(githubApiService.getGithubUserInfo(ACCESS_TOKEN_FOR_TEST)).thenReturn(SAVED_GITHUB_USER_INFO_VO);
        when(userGithubService.getUserByGithubInfo(ACCESS_TOKEN_FOR_TEST)).thenReturn(SAVED_USER);

        given().
                queryParam("code", CODE_FOR_TEST).
                when().
                get(baseUrl() + "/api/v1/tecobrary/auth").
                then().
                log().ifValidationFails().
                statusCode(200).
                contentType(JSON).
                body("user.email", equalTo(SAVED_USER_EMAIL_VALUE)).
                body("user.name", equalTo(SAVED_USER_NAME_VALUE)).
                body("user.avatarUrl", equalTo(SAVED_USER_AVATAR_URL_VALUE)).
                body("user.authorization", equalTo(SAVED_USER_AUTH_VALUE));
    }

    @DisplayName("[GET] /github/user/, 새로 가입하는 user 에 대한 json 이 성공적으로 반환된다.")
    @Test
    void getGithubUserInformationWithNewUser() {
        when(githubApiService.getGithubAccessToken(CODE_FOR_TEST)).thenReturn(ACCESS_TOKEN_FOR_TEST);
        when(githubApiService.getGithubUserInfo(ACCESS_TOKEN_FOR_TEST)).thenReturn(TEST_GITHUB_USER_INFO_VO);
        when(githubApiService.getGithubUserEmail(ACCESS_TOKEN_FOR_TEST)).thenReturn(TEST_USER_EMAIL_VALUE);
        when(userGithubService.getUserByGithubInfo(ACCESS_TOKEN_FOR_TEST)).thenReturn(TEST_USER);

        given().
                queryParam("code", CODE_FOR_TEST).
        when().
                get(baseUrl() + "/api/v1/tecobrary/auth").
        then().
                log().ifValidationFails().
                statusCode(200).
                contentType(JSON).
                body("user.email", equalTo(TEST_USER_EMAIL_VALUE)).
                body("user.name", equalTo(TEST_USER_NAME_VALUE)).
                body("user.avatarUrl", equalTo(TEST_USER_AVATAR_URL_VALUE)).
                body("user.authorization", equalTo(TEST_USER_AUTH_VALUE));
    }
}