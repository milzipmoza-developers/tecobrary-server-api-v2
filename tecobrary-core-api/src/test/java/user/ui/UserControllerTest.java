package user.ui;

import com.woowacourse.tecobrary.user.ui.dto.UserAuthDto;
import com.woowacourse.tecobrary.user.ui.dto.UserNameDto;
import common.util.AcceptanceTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;
import user.common.UserStatic;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

public class UserControllerTest extends AcceptanceTestUtils implements UserStatic {

    @DisplayName("[GET] /users/all, 총 회원 수를 조회한다.")
    @Test
    void successfullyCountOfUser() {
        given(this.spec).
                accept(JSON).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY, responseFields(
                        fieldWithPath("total").description("all_user_count")
                ))).
        when().
                get(baseUrl("/users/all")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(200).
                contentType(JSON).
                body("total", is(32));
    }

    @DisplayName("[GET] /users?page=1&number=10, 회원 목록을 조회한다.")
    @Test
    void successfullyFindUsers() {
        given(this.spec).
                param("page",1).
                param("number", 10).
                accept(JSON).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY, requestParameters(
                        parameterWithName("page").description("page"),
                        parameterWithName("number").description("number")),
                        responseFields(
                                fieldWithPath("[0].githubId").description("id_target_githubId"),
                                fieldWithPath("[0].email").description("id_target_email"),
                                fieldWithPath("[0].name").description("id_target_name"),
                                fieldWithPath("[0].avatarUrl").description("id_target_avatar_url"),
                                fieldWithPath("[0].authorization").description("id_target_authorization")
                        ))).
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

    @DisplayName("[GET] /users?page=6&number=10, 존재하지 않는 페이지의 회원을 조회하면, 회원목록 조회를 실패한다.")
    @Test
    void failFindUsers() {
        given(this.spec).
                param("page",6).
                param("number", 10).
                accept(JSON).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY, requestParameters(
                        parameterWithName("page").description("page"),
                        parameterWithName("number").description("number")))).
        when().
                get(baseUrl("/users")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(200).
                contentType(JSON).
                body("users.size()", is(0));
    }

    @DisplayName("[GET] /users?page=string&number=string, 페이지와 페이지에 대한 회원 수에 문자를 입력하면, 회원목록 조회를 실패한다.")
    @Test
    void failFindUsersInvalidParams() {
        given(this.spec).
                params("page","string","number", "string").
                accept(JSON).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY, requestParameters(
                        parameterWithName("page").description("page"),
                        parameterWithName("number").description("number")))).
        when().
                get(baseUrl("/users")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(400);
    }

    @DisplayName("[GET] /users/:id, 회원을 조회한다.")
    @Test
    void successfullyFindUserById() {
        given(this.spec).
                accept(JSON).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY, pathParameters(
                        parameterWithName("id").description("userId")),
                        responseFields(
                                fieldWithPath("githubId").description("id_target_githubId"),
                                fieldWithPath("email").description("id_target_email"),
                                fieldWithPath("name").description("id_target_name"),
                                fieldWithPath("avatarUrl").description("id_target_avatar_url"),
                                fieldWithPath("authorization").description("id_target_authorization")
                        ))).
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

    @DisplayName("[PATCH] /users, 회원의 닉네임을 수정한다.")
    @DirtiesContext
    @Test
    void successfullyUpdateUserNickName() {
        UserNameDto userNameDto = new UserNameDto(1L, "조로");

        given(this.spec).
                contentType(JSON).
                accept(JSON).
                body(userNameDto).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestFields(
                                fieldWithPath("id").description("target_user_id"),
                                fieldWithPath("newName").description("new_name")),
                        responseFields(
                                fieldWithPath("githubId").description("id_target_githubId"),
                                fieldWithPath("email").description("id_target_email"),
                                fieldWithPath("name").description("id_target_name"),
                                fieldWithPath("avatarUrl").description("id_target_avatar_url"),
                                fieldWithPath("authorization").description("id_target_authorization")
                        ))).
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

    @DisplayName("[POST] /users, 회원의 권한을 수정한다.")
    @DirtiesContext
    @Test
    void successfullyUpdateUserAuth() {
        UserAuthDto userAuthDto = new UserAuthDto(1L, "MANAGER");
        given(this.spec).
                contentType(JSON).
                accept(JSON).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestFields(
                                fieldWithPath("id").description("target_user_id"),
                                fieldWithPath("authorization").description("new_authorization")),
                        responseFields(
                                fieldWithPath("githubId").description("id_target_githubId"),
                                fieldWithPath("email").description("id_target_email"),
                                fieldWithPath("name").description("id_target_name"),
                                fieldWithPath("avatarUrl").description("id_target_avatar_url"),
                                fieldWithPath("authorization").description("id_target_authorization")
                        ))).
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
