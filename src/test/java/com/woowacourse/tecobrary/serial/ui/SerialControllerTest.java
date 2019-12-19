package com.woowacourse.tecobrary.serial.ui;

import com.woowacourse.tecobrary.common.util.AcceptanceTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import static com.woowacourse.tecobrary.serial.exception.NotFoundSerialNumberException.NOT_FOUND_SERIAL_NUMBER_EXCEPTION_MESSAGE;
import static com.woowacourse.tecobrary.serial.ui.SerialController.DELETE_SUCCESS_MESSAGE;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

class SerialControllerTest extends AcceptanceTestUtils {

    @DisplayName("[DELETE] /serials?number=1, 도서의 일련번호를 삭제한다.")
    @DirtiesContext
    @Test
    public void successfullyRemoveSerial() {
        given(this.spec).
                param("number", 1).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestParameters(
                                parameterWithName("number").description("serial_number")),
                        responseFields(
                                fieldWithPath("message").description("success_to_delete_serial_message")))).
        when().
                delete(baseUrl("/serials")).
        then().
                log().ifError().
                log().ifValidationFails().
                contentType(JSON).
                statusCode(200).
                body("message", is(DELETE_SUCCESS_MESSAGE));
    }

    @DisplayName("[DELETE] /serials?number=10000000, 도서의 일련번호가 존재하지 않으면, 일련번호 삭제를 실패한다.")
    @Test
    public void failedRemoveSerialNotExistId() {
        given(this.spec).
                param("number", 10_000_000).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestParameters(
                                parameterWithName("number").description("serial_number")),
                        responseFields(
                                fieldWithPath("message").description("not_found_serial_number_exception_message")))).
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