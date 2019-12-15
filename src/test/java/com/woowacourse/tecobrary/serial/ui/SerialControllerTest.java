package com.woowacourse.tecobrary.serial.ui;

import com.woowacourse.tecobrary.common.util.RestAssuredTestUtils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
class SerialControllerTest extends RestAssuredTestUtils {

    private RequestSpecification spec;

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        this.spec = new RequestSpecBuilder()
                .addFilter(documentationConfiguration(restDocumentation))
                .build();
    }

    @DisplayName("[DELETE] /serials?number=1, 성공적으로 serial 1 을 삭제한다.")
    @DirtiesContext
    @Test
    public void successfullyRemoveSerial() {
        given(this.spec).
                param("number", 1).
                filter(document("{class-name}/{method-name}",
                        requestParameters(
                                parameterWithName("number").description("serial_number")),
                        responseFields(
                                fieldWithPath("message").description("해당 일련번호를 삭제하였습니다.")))).
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