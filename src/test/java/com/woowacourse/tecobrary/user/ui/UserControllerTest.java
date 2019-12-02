package com.woowacourse.tecobrary.user.ui;

import com.woowacourse.tecobrary.common.util.RestAssuredTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
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
}
