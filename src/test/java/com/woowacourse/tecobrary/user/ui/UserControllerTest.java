package com.woowacourse.tecobrary.user.ui;

import com.woowacourse.tecobrary.common.util.RestAssuredTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

public class UserControllerTest extends RestAssuredTestUtils {

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

    @DisplayName("[GET] /users?page=1&number=2")
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
                body("users[0].userGithubInfo.githubId", equalTo("123456")).
                body("users[1].userGithubInfo.githubId", equalTo("940720"));
    }

    @DisplayName("[GET] /users?page=2&number=2")
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

    @DisplayName("[GET] /users?page=string&number=string")
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
}
