package com.woowacourse.tecobrary.web;

import com.woowacourse.tecobrary.web.common.AcceptanceTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

class HomeControllerTest extends AcceptanceTestUtils {

    @DisplayName("[GET] /api/v2 응답을 성공적으로 받는다.")
    @Test
    void home() {
        given().
        when().
                get(baseUrl("/api/v2")).
        then().
                statusCode(200).
                body(is("Hello. This is Tecobrary API v2. Welcome !"));
    }
}