package com.woowacourse.tecobrary.user.ui;

import com.woowacourse.tecobrary.common.util.RestAssuredTestUtils;
import com.woowacourse.tecobrary.user.common.UserStatic;
import com.woowacourse.tecobrary.user.ui.dto.UserAuthDto;
import com.woowacourse.tecobrary.user.ui.dto.UserNameDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class UserControllerTest extends RestAssuredTestUtils implements UserStatic {

    @DisplayName("[GET] /users/all, 회원 수를 조회한다.")
    @Test
    void successfullyCountOfUser() {
        given().
                accept(JSON).
        when().
                get(baseUrl("/users/all")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(200).
                contentType(JSON).
                body("total", is(32));
    }

    @DisplayName("[GET] /users?page=1&number=10, 10개씩 1페이지 회원 목록을 조회한다.")
    @Test
    void successfullyFindUsers() {
        given().
                param("page",1).
                param("number", 10).
                accept(JSON).
        when().
                get(baseUrl("/users")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(200).
                contentType(JSON).
                body("size()", is(10)).
                body("[0].githubId", equalTo(SAVED_GITHUB_ID_AT_ID_01)).
                body("[0].email", equalTo(SAVED_USER_EMAIL_VALUE_AT_ID_01)).
                body("[0].name", equalTo(SAVED_USER_NAME_VALUE_AT_ID_01)).
                body("[0].avatarUrl", equalTo(SAVED_USER_AVATAR_URL_VALUE_AT_ID_01)).
                body("[0].authorization", equalTo(SAVED_USER_AUTH_VALUE_AT_ID_01)).

                body("[1].githubId", equalTo(SAVED_GITHUB_ID_AT_ID_02)).
                body("[1].email", equalTo(SAVED_USER_EMAIL_VALUE_AT_ID_02)).
                body("[1].name", equalTo(SAVED_USER_NAME_VALUE_AT_ID_02)).
                body("[1].avatarUrl", equalTo(SAVED_USER_AVATAR_URL_VALUE_AT_ID_02)).
                body("[1].authorization", equalTo(SAVED_USER_AUTH_VALUE_AT_ID_02));
    }

    @DisplayName("[GET] /users?page=6&number=10, 10개씩 6페이지 회원 목록을 조회하면 아무것도 없다.")
    @Test
    void failFindUsers() {
        given().
                param("page",6).
                param("number", 10).
                accept(JSON).
        when().
                get(baseUrl("/users")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(200).
                contentType(JSON).
                body("users.size()", is(0));
    }

    @DisplayName("[GET] /users?page=string&number=string, 적절하지 않은 쿼리 파라미터 값을 보내면 Bad Request 응답을 받는다.")
    @Test
    void failFindUsersInvalidParams() {
        given().
                params("page","string","number", "string").
                accept(JSON).
        when().
                get(baseUrl("/users")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(400);
    }

    @DisplayName("[GET] /users/:id, id로 특정 유저 조회를 한다.")
    @Test
    void successfullyFindUserById() {
        given().
                accept(JSON).
        when().
                get(baseUrl("/users/{id}"),1).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(200).
                contentType(JSON).
                body("githubId", equalTo(SAVED_GITHUB_ID_AT_ID_01)).
                body("email", equalTo(SAVED_USER_EMAIL_VALUE_AT_ID_01)).
                body("name", equalTo(SAVED_USER_NAME_VALUE_AT_ID_01)).
                body("avatarUrl", equalTo(SAVED_USER_AVATAR_URL_VALUE_AT_ID_01)).
                body("authorization", equalTo(SAVED_USER_AUTH_VALUE_AT_ID_01));
    }

    @DisplayName("[PATCH] /users, 유저 닉네임을 업데이트한다.")
    @DirtiesContext
    @Test
    void successfullyUpdateUserNickName() {
        UserNameDto userNameDto = new UserNameDto(1L, "조로");

        given().
                contentType(JSON).
                accept(JSON).
                body(userNameDto).
        when().
                patch(baseUrl("/users")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(200).
                contentType(JSON).
                body("githubId", equalTo(SAVED_GITHUB_ID_AT_ID_01)).
                body("email", equalTo(SAVED_USER_EMAIL_VALUE_AT_ID_01)).
                body("name", equalTo("조로")).
                body("avatarUrl", equalTo(SAVED_USER_AVATAR_URL_VALUE_AT_ID_01)).
                body("authorization", equalTo(SAVED_USER_AUTH_VALUE_AT_ID_01));
    }

    @DisplayName("[POST] /users, 회원의 권한을 업데이트한다.")
    @DirtiesContext
    @Test
    void successfullyUpdateUserAuth() {
        UserAuthDto userAuthDto = new UserAuthDto(1L, "MANAGER");
        given().
                contentType(JSON).
                accept(JSON).
                body(userAuthDto).
        when().
                post(baseUrl("/users")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(200).
                contentType(JSON).
                body("githubId", equalTo(SAVED_GITHUB_ID_AT_ID_01)).
                body("email", equalTo(SAVED_USER_EMAIL_VALUE_AT_ID_01)).
                body("name", equalTo(SAVED_USER_NAME_VALUE_AT_ID_01)).
                body("avatarUrl", equalTo(SAVED_USER_AVATAR_URL_VALUE_AT_ID_01)).
                body("authorization", equalTo("MANAGER"));
    }
}
