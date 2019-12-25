package com.woowacourse.tecobrary.renthistory.ui;

import com.woowacourse.tecobrary.common.util.AcceptanceTestUtils;
import com.woowacourse.tecobrary.librarybook.common.LibraryBookStatic;
import com.woowacourse.tecobrary.renthistory.common.RentHistoryStatic;
import com.woowacourse.tecobrary.serial.common.SerialStatic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

class RentHistoryReadControllerTest extends AcceptanceTestUtils implements RentHistoryStatic, LibraryBookStatic, SerialStatic {

    @DisplayName("[GET] /rents/:userId, 회원의 도서대여 목록을 조회한다.")
    @Test
    void successfullyFindAllRentHistoryByUser() {

        given(this.spec).
                accept(JSON).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        pathParameters(
                                parameterWithName("id").description("user_id")),
                        responseFields(
                                fieldWithPath("[0].id").description("rent_history_id"),
                                fieldWithPath("[0].serial").description("rent_book_serial_number_id"),
                                fieldWithPath("[0].title").description("rent_book_title_id"),
                                fieldWithPath("[0].userId").description("who_rent_book"),
                                fieldWithPath("[0].rentDate").description("rented_time")))).
        when().
                get(baseUrl("/rents/{id}"), TEST_RENT_HISTORY_USER_ID_16).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(200).
                contentType(JSON).
                body("[0].id", equalTo(TEST_RENT_HISTORY_ID.intValue())).
                body("[0].serial", equalTo(TEST_SERIAL_NUMBER.intValue())).
                body("[0].title", equalTo(TEST_LIBRARY_BOOK_TITLE_19)).
                body("[0].userId", equalTo(TEST_RENT_HISTORY_USER_ID_16.intValue())).
                body("[0].rentDate", equalTo("2019-11-07T00:51:40")).

                body("[1].id", equalTo(TEST_RENT_HISTORY_ID_02.intValue())).
                body("[1].serial", equalTo(TEST_SERIAL_NUMBER_02.intValue())).
                body("[1].title", equalTo(TEST_LIBRARY_BOOK_TITLE_07)).
                body("[1].userId", equalTo(TEST_RENT_HISTORY_USER_ID_16.intValue())).
                body("[1].rentDate", equalTo("2019-11-11T03:48:41"));
    }

    @DisplayName("[GET] /histories/:userId, 회원의 도서반납 목록을 조회한다.")
    @Test
    void successfullyFindAllReturnHistoryByUser() {

        given(this.spec).
                accept(JSON).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        pathParameters(
                                parameterWithName("user_id").description("반납 목록을 조회할 유저의 id")),
                        responseFields(
                                fieldWithPath("[0].id").description("RentHistory 테이블의 id"),
                                fieldWithPath("[0].serial").description("해당 도서의 Serial Number"),
                                fieldWithPath("[0].title").description("해당 도서의 제목"),
                                fieldWithPath("[0].userId").description("해당 도서를 대여한 유저 id"),
                                fieldWithPath("[0].rentDate").description("해당 도서를 대여한 날짜 및 시간"),
                                fieldWithPath("[0].returnDate").description("해당 도서를 반납한 날짜 및 시간")))).
        when().
                get(baseUrl("/histories/{user_id}"), 33).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(200).
                contentType(JSON).
                body("[0].id", equalTo(45)).
                body("[0].serial", equalTo(10)).
                body("[0].title", equalTo("객체 지향과 디자인 패턴 (개발자가 반드시 정복해야 할)")).
                body("[0].userId", equalTo(33)).
                body("[0].rentDate", equalTo("2019-11-15T03:08:39")).
                body("[0].returnDate", equalTo("2019-11-15T04:05:49"));
    }
}