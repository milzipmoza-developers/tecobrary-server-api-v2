package com.woowacourse.tecobrary.serial.ui;

import com.woowacourse.tecobrary.common.util.RestAssuredTestUtils;
import com.woowacourse.tecobrary.serial.ui.dto.SerialCreateRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

class SerialControllerTest extends RestAssuredTestUtils {

    @DisplayName("[post] /serials, id 에 해당하는 도서에 serial 을 추가")
    @Test
    void successfullyCreateSerial() {
        SerialCreateRequestDto serialCreateRequestDto = new SerialCreateRequestDto(1L, 1000L);
        given().
                contentType(JSON).
                body(serialCreateRequestDto).
        when().
                post(baseUrl("/serials")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(200).
                contentType(JSON).
                body("message", is("등록에 성공하였습니다.")).
                body("serial.status", is(false)).
                body("serial.serialNumber", is(1000)).
                body("serial.bookId", is(1)).
                body("serial.updatedAt", notNullValue()).
                body("serial.createdAt", notNullValue());
    }
}