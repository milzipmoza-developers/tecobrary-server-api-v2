package serial.ui;

import com.woowacourse.tecobrary.serial.ui.dto.SerialCreateRequestDto;
import common.util.AcceptanceTestUtils;
import librarybook.common.LibraryBookStatic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import static com.woowacourse.tecobrary.serial.exception.NotFoundSerialNumberException.NOT_FOUND_SERIAL_NUMBER_EXCEPTION_MESSAGE;
import static com.woowacourse.tecobrary.serial.exception.NotFoundSerialTargetException.NOT_FOUND_SERIAL_TARGET_EXCEPTION_MESSAGE;
import static com.woowacourse.tecobrary.serial.exception.UniqueConstraintException.UNIQUE_CONSTRAINT_EXCEPTION_MESSAGE;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

class SerialCreateReadControllerTest extends AcceptanceTestUtils implements LibraryBookStatic {

    @DisplayName("[GET] /serials?bookId, 도서의 일련번호 목록을 조회한다.")
    @Test
    void successfullyGetBookIdSerials() {
        given(this.spec).
                queryParam("bookId", 9).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestParameters(
                                parameterWithName("bookId").description("book_id")),
                        responseFields(
                                fieldWithPath("[0].serialNumber").description("book_serial_number"),
                                fieldWithPath("[0].status").description("book_rent_status")))).
        when().
                get(baseUrl("/serials")).
        then().
                log().everything().
                log().ifError().
                log().ifValidationFails().
                contentType(JSON).
                body("size()", equalTo(4)).
                body("[0].serialNumber", equalTo(10)).
                body("[0].status", is(false)).
                body("[2].serialNumber", equalTo(12)).
                body("[2].status", is(false));
    }

    @DisplayName("[GET] /serials?bookId=10000000, 도서가 존재하지 않으면, 도서의 일련번호 조회를 실패한다.")
    @Test
    void failedGetBookIdSerialsBadRequest() {
        given(this.spec).
                queryParam("bookId", 10_000_000).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestParameters(
                                parameterWithName("bookId").description("book_id")),
                        responseFields(
                                fieldWithPath("message").description("fail_to_get_serial_message")))).
        when().
                get(baseUrl("/serials")).
        then().
                log().ifError().
                log().ifValidationFails().
                contentType(JSON).
                body("message", is(NOT_FOUND_SERIAL_TARGET_EXCEPTION_MESSAGE));
    }

    @DisplayName("[POST] /serials, 도서의 일련번호를 등록한다.")
    @DirtiesContext
    @Test
    void successfullyCreateSerial() {
        SerialCreateRequestDto serialCreateRequestDto = new SerialCreateRequestDto(1L, 1000L);
        given(this.spec).
                contentType(JSON).
                body(serialCreateRequestDto).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestFields(
                                fieldWithPath("bookId").description("target_book_id"),
                                fieldWithPath("serialNumber").description("new_serial_number")),
                        responseFields(
                                fieldWithPath("message").description("success_to_register_serial_message"),
                                fieldWithPath("serial.status").description("book_rent_status"),
                                fieldWithPath("serial.id").description("serial_id"),
                                fieldWithPath("serial.serialNumber").description("serial_number"),
                                fieldWithPath("serial.bookId").description("book_id"),
                                fieldWithPath("serial.updatedAt").description("serial_update_time"),
                                fieldWithPath("serial.createdAt").description("serial_create_time")))).
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

    @DisplayName("[POST] /serials, 도서가 존재하지 않으면, 도서의 일련번호 등록을 실패한다.")
    @Test
    void failedCreateSerialNotFoundSerialTarget() {
        SerialCreateRequestDto serialCreateRequestDto = new SerialCreateRequestDto(1_000_000L, 1000L);
        given(this.spec).
                contentType(JSON).
                body(serialCreateRequestDto).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestFields(
                                fieldWithPath("bookId").description("target_book_id"),
                                fieldWithPath("serialNumber").description("new_serial_number")),
                        responseFields(
                                fieldWithPath("message").description("not_found_serial_target_exception_message")))).
        when().
                post(baseUrl("/serials")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(400).
                contentType(JSON).
                body("message", is(NOT_FOUND_SERIAL_TARGET_EXCEPTION_MESSAGE));
    }

    @DisplayName("[POST] /serials, 일련번호가 이미 존재하면, 도서의 일련번호 등록을 실패한다.")
    @Test
    void failedCreateSerialUniqueConstraint() {
        SerialCreateRequestDto serialCreateRequestDto = new SerialCreateRequestDto(1L, 1L);
        given(this.spec).
                contentType(JSON).
                body(serialCreateRequestDto).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestFields(
                                fieldWithPath("bookId").description("target_book_id"),
                                fieldWithPath("serialNumber").description("new_serial_number")),
                        responseFields(
                                fieldWithPath("message").description("unique_constraint_exception_message")))).
        when().
                post(baseUrl("/serials")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(400).
                contentType(JSON).
                body("message", is(UNIQUE_CONSTRAINT_EXCEPTION_MESSAGE));
    }

    @DisplayName("[GET] /serials/:serialNumber, 일련번호로 도서를 조회한다.")
    @Test
    void successfullyGetBookBySerialNumber() {
        given(this.spec).
                contentType(JSON).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY, pathParameters(
                        parameterWithName("serialNumber").description("serial_number")),
                        responseFields(
                                fieldWithPath("id").description("target_book_id"),
                                fieldWithPath("image").description("target_book_image"),
                                fieldWithPath("title").description("target_book_title"),
                                fieldWithPath("author").description("target_book_author"),
                                fieldWithPath("publisher").description("target_book_publisher"),
                                fieldWithPath("isbn").description("target_book_isbn"),
                                fieldWithPath("description").description("target_book_desc")
                        ))).
        when().
                get(baseUrl("/serials/{serialNumber}"), 1).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(200).
                contentType(JSON).
                body("id", is(TEST_LIBRARY_BOOK_ID.intValue())).
                body("image", is(TEST_LIBRARY_BOOK_IMAGE)).
                body("title", is(TEST_LIBRARY_BOOK_TITLE)).
                body("author", is(TEST_LIBRARY_BOOK_AUTHOR)).
                body("publisher", is(TEST_LIBRARY_BOOK_PUBLISHER)).
                body("isbn", is(TEST_LIBRARY_BOOK_ISBN)).
                body("description", is(TEST_LIBRARY_BOOK_DESCRIPTION));
    }

    @DisplayName("[GET] /serials/:serialNumber, 도서의 일련번호가 존재하지 않으면, 도서조회를 실패한다.")
    @Test
    void failedGetBookBySerialNumberNotExist() {
        given(this.spec).
                contentType(JSON).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY, pathParameters(
                        parameterWithName("serialNumber").description("serial_number")),
                        responseFields(
                                fieldWithPath("message").description("not_found_serial_number_exception_message")))).
        when().
                get(baseUrl("/serials/{serialNumber}"), 10000000).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(400).
                contentType(JSON).
                body("message", is(NOT_FOUND_SERIAL_NUMBER_EXCEPTION_MESSAGE));
    }
}