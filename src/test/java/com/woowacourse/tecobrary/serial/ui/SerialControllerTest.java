package com.woowacourse.tecobrary.serial.ui;

import com.woowacourse.tecobrary.common.util.RestAssuredTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import static com.woowacourse.tecobrary.serial.exception.NotFoundSerialNumberException.NOT_FOUND_SERIAL_NUMBER_EXCEPTION_MESSAGE;
import static com.woowacourse.tecobrary.serial.ui.SerialController.DELETE_SUCCESS_MESSAGE;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;

class SerialControllerTest extends RestAssuredTestUtils {

    @DisplayName("[DELETE] /serials?number=1, 성공적으로 serial 1 을 삭제한다.")
    @DirtiesContext
    @Test
    public void successfullyRemoveSerial() {
        given().
                param("number", 1).
        when().
                delete(baseUrl("/serials")).
        then().
                log().ifError().
                log().ifValidationFails().
                contentType(JSON).
                statusCode(200).
                body("message", is(DELETE_SUCCESS_MESSAGE));
    }

    @DisplayName("[DELETE] /serials?number=10000000, 존재하지 않는 일련번호에 대해 Bad Request 를 응답 받는다.")
    @Test
    public void failedRemoveSerialNotExistId() {
        given().
                param("number", 10_000_000).
        when().
                delete(baseUrl("/serials")).
        then().
                log().ifError().
                log().ifValidationFails().
                contentType(JSON).
                statusCode(400).
                body("message", is(NOT_FOUND_SERIAL_NUMBER_EXCEPTION_MESSAGE));
    }
}