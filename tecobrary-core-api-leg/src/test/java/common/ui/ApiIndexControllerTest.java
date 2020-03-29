package common.ui;

import common.util.AcceptanceTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ApiIndexControllerTest extends AcceptanceTestUtils {

    @DisplayName("[GET] /api/v1 응답을 성공적으로 받는다.")
    @Test
    void home() {
        given().
        when().
                get(baseUrl("/")).
        then().
                statusCode(200).
                body(is("Hello. This is Tecobrary API v1. Welcome !"));
    }
}