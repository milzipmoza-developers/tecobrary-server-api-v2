package com.woowacourse.tecobrary.user.ui;

import com.woowacourse.tecobrary.common.util.RestAssuredTestUtils;
import com.woowacourse.tecobrary.user.common.UserStatic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
                body("total", is(2));
    }

    @DisplayName("[GET] /users?page=1&number=2, 2개씩 1페이지 회원 목록을 조회한다.")
    @Test
    void successfullyFindUsers() {
        given().
                params("page",1,"number", 2).
                accept(JSON).
        when().
                get(baseUrl("/users")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(200).
                contentType(JSON).
                body("users.size()", is(2)).
                body("users[0].githubId", equalTo(SAVED_GITHUB_ID)).
                body("users[0].email", equalTo(SAVED_USER_EMAIL_VALUE)).
                body("users[0].name", equalTo(SAVED_USER_NAME_VALUE)).
                body("users[0].avatarUrl", equalTo(SAVED_USER_AVATAR_URL_VALUE)).
                body("users[0].authorization", equalTo(SAVED_USER_AUTH_VALUE)).

                body("users[1].githubId", equalTo(SECOND_SAVED_GITHUB_ID)).
                body("users[1].email", equalTo(SECOND_SAVED_USER_EMAIL_VALUE)).
                body("users[1].name", equalTo(SECOND_SAVED_USER_NAME_VALUE)).
                body("users[1].avatarUrl", equalTo(SECOND_SAVED_USER_AVATAR_URL_VALUE)).
                body("users[1].authorization", equalTo(SECOND_SAVED_USER_AUTH_VALUE));
    }

    @DisplayName("[GET] /users?page=2&number=2, 2개씩 2페이지 회원 목록을 조회하면 아무것도 없다.")
    @Test
    void failFindUsers() {
        given().
                params("page",2,"number", 2).
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
    void failFindUsers2() {
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
                body("user.githubId", equalTo(SAVED_GITHUB_ID)).
                body("user.email", equalTo(SAVED_USER_EMAIL_VALUE)).
                body("user.name", equalTo(SAVED_USER_NAME_VALUE)).
                body("user.avatarUrl", equalTo(SAVED_USER_AVATAR_URL_VALUE)).
                body("user.authorization", equalTo(SAVED_USER_AUTH_VALUE));
    }
}
