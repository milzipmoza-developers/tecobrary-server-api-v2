package com.woowacourse.tecobrary.user.ui;

import com.woowacourse.tecobrary.common.util.AcceptanceTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

public class GithubOAuthControllerTest extends AcceptanceTestUtils {

    @DisplayName("[GET] /github/user/oauth 로 요청시 성공적으로 리다이렉트 된다.")
    @Test
    void githubConfirmAuthentication() {
        given(this.spec).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY)).
        when().
                get(baseUrl("/github/user/oauth")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(200);
    }
}
