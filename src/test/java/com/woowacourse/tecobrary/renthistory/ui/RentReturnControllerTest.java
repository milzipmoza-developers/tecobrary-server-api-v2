/*
 * @(#) RentReturnControllerTest.java
 *
 * v 1.0.0
 *
 * 2019.12.11
 *
 * Copyright (c) 2019 woowacourse, mizipmoza-developers, thedevluffy
 * All rights reserved
 */

package com.woowacourse.tecobrary.renthistory.ui;

import com.woowacourse.tecobrary.common.util.AcceptanceTestUtils;
import com.woowacourse.tecobrary.librarybook.common.LibraryBookStatic;
import com.woowacourse.tecobrary.renthistory.ui.dto.RentRequestDto;
import com.woowacourse.tecobrary.renthistory.ui.dto.ReturnRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import static com.woowacourse.tecobrary.renthistory.application.AlreadyRentBookException.ALREADY_RENT_BOOK_EXCEPTION_MESSAGE;
import static com.woowacourse.tecobrary.renthistory.application.AlreadyReturnBookException.ALREADY_RETURNED_BOOK_EXCEPTION_MESSAGE;
import static com.woowacourse.tecobrary.renthistory.application.RentReturnService.RENT_SUCCESS_MESSAGE;
import static com.woowacourse.tecobrary.renthistory.application.RentReturnService.RETURN_SUCCESS_MESSAGE;
import static com.woowacourse.tecobrary.renthistory.domain.NotPermittedUserException.NOT_PERMITTED_USER_EXCEPTION_MESSAGE;
import static com.woowacourse.tecobrary.serial.exception.NotFoundSerialNumberException.NOT_FOUND_SERIAL_NUMBER_EXCEPTION_MESSAGE;
import static com.woowacourse.tecobrary.user.command.application.NotFoundUserException.NOT_FOUND_USER_EXCEPTION_MESSAGE;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

public class RentReturnControllerTest extends AcceptanceTestUtils implements LibraryBookStatic {

    @DisplayName("[POST] /rents, 도서를 대여한다.")
    @DirtiesContext
    @Test
    void successfullyRentABook() {
        RentRequestDto rentRequestDto = new RentRequestDto(1L, 1L);

        given(this.spec).
                body(rentRequestDto).
                accept(JSON).
                contentType(JSON).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestFields(
                                fieldWithPath("serial").description("target_serial"),
                                fieldWithPath("userId").description("rent_request_user_id")),
                        responseFields(
                                fieldWithPath("rentInfo.title").description("rent_book_title"),
                                fieldWithPath("rentInfo.serialNumber").description("rent_book_serial_number"),
                                fieldWithPath("rentInfo.status").description("rent_book_status"),
                                fieldWithPath("message").description("대여에 성공하였습니다.")))).
        when().
                post(baseUrl("/rents")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(200).
                body("rentInfo.title", is(TEST_LIBRARY_BOOK_TITLE)).
                body("rentInfo.serialNumber", is(1)).
                body("rentInfo.status", is(true)).
                body("message", is(RENT_SUCCESS_MESSAGE));
    }

    @DisplayName("[POST] /rents, 도서를 대여하는 회원이 존재하지 않으면, 대여를 실패한다.")
    @Test
    void failedRentWithNotExistUser() {
        given(this.spec).
                accept(JSON).
                contentType(JSON).
                body(new RentRequestDto(1L, 1_000_000L)).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestFields(
                                fieldWithPath("serial").description("target_serial"),
                                fieldWithPath("userId").description("rent_request_user_id")),
                        responseFields(
                                fieldWithPath("message").description("not_found_user_exception_message")))).
        when().
                post(baseUrl("/rents")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(400).
                body("message", is(NOT_FOUND_USER_EXCEPTION_MESSAGE));
    }

    @DisplayName("[POST] /rents, 대여 할 도서의 일련번호가 존재하지 않으면, 도서대여를 실패한다.")
    @Test
    void failedRentWithNotExistSerial() {
        given(this.spec).
                accept(JSON).
                contentType(JSON).
                body(new RentRequestDto(1_000_000L, 1L)).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestFields(
                                fieldWithPath("serial").description("target_serial"),
                                fieldWithPath("userId").description("rent_request_user_id")),
                        responseFields(
                                fieldWithPath("message").description("not_found_serial_number_exception_message")))).
        when().
                post(baseUrl("/rents")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(400).
                body("message", is(NOT_FOUND_SERIAL_NUMBER_EXCEPTION_MESSAGE));
    }

    @DisplayName("[POST] /rents, 대여할 책이 이미 대여 중이면, 도서대여를 실패한다.")
    @Test
    void failedRentWithAlreadyRentSerial() {
        RentRequestDto rentRequestDto = new RentRequestDto(5L, 1L);

        given(this.spec).
                body(rentRequestDto).
                accept(JSON).
                contentType(JSON).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestFields(
                                fieldWithPath("serial").description("target_serial"),
                                fieldWithPath("userId").description("rent_request_user_id")),
                        responseFields(
                                fieldWithPath("rentInfo.serial").description("target_serial"),
                                fieldWithPath("rentInfo.userId").description("rent_request_user_id"),
                                fieldWithPath("message").description("already_rent_book_exception_message")))).
        when().
                post(baseUrl("/rents")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(400).
                body("rentInfo.serial", is(5)).
                body("message", is(ALREADY_RENT_BOOK_EXCEPTION_MESSAGE));
    }

    @DisplayName("[PATCH] /rents, 대여한 도서를 반납한다.")
    @DirtiesContext
    @Test
    void successfullyReturnABook() {
        RentRequestDto rentRequestDto = new RentRequestDto(131L, 21L);

        given(this.spec).
                body(rentRequestDto).
                accept(JSON).
                contentType(JSON).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestFields(
                                fieldWithPath("serial").description("target_serial"),
                                fieldWithPath("userId").description("return_request_user_id")),
                        responseFields(
                                fieldWithPath("returnInfo.title").description("return_target_book_title"),
                                fieldWithPath("returnInfo.serialNumber").description("return_target_book_serial_number"),
                                fieldWithPath("returnInfo.returnedAt").description("book_returned_time"),
                                fieldWithPath("message").description("반납에 성공하였습니다.")))).
        when().
                patch(baseUrl("/rents")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(200).
                body("returnInfo.title", is("전문가를 위한 스프링 5 (스프링을 깊이 있게, 철저하게!)")).
                body("returnInfo.serialNumber", is(131)).
                body("returnInfo.returnedAt", is(notNullValue())).
                body("message", is(RETURN_SUCCESS_MESSAGE));
    }

    @DisplayName("[PATCH] /rents, 도서를 반납하는 회원이 존재하지 않으면, 도서반납을 실패한다.")
    @Test
    void failedReturnWithNotExistUser() {
        given(this.spec).
                accept(JSON).
                contentType(JSON).
                body(new ReturnRequestDto(2L, 1_000_000L)).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestFields(
                                fieldWithPath("serial").description("target_serial"),
                                fieldWithPath("userId").description("return_request_user_id")),
                        responseFields(
                                fieldWithPath("message").description("not_found_user_exception_message")))).
        when().
                patch(baseUrl("/rents")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(400).
                body("message", is(NOT_FOUND_USER_EXCEPTION_MESSAGE));
    }

    @DisplayName("[PATCH] /rents, 도서를 반납하는 회원과 대여한 회원이 일치하지 않으면, 도서반납을 실패한다.")
    @Test
    void failedReturnWithNotRentUser() {
        given(this.spec).
                accept(JSON).
                contentType(JSON).
                body(new ReturnRequestDto(131L, 4L)).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestFields(
                                fieldWithPath("serial").description("target_serial"),
                                fieldWithPath("userId").description("return_request_user_id")),
                        responseFields(
                                fieldWithPath("statusCode").description("status_code"),
                                fieldWithPath("message").description("not_permitted_user_exception_message")))).
        when().
                patch(baseUrl("/rents")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(403).
                body("statusCode", is(403)).
                body("message", is(NOT_PERMITTED_USER_EXCEPTION_MESSAGE));
    }

    @DisplayName("[PATCH] /rents, 반납하는 도서의 일련번호가 존재하지 않으면, 도서반납을 실패한다.")
    @Test
    void failedReturnWithNotExistSerial() {
        given(this.spec).
                accept(JSON).
                contentType(JSON).
                body(new ReturnRequestDto(1_000_000L, 1L)).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestFields(
                                fieldWithPath("serial").description("target_serial"),
                                fieldWithPath("userId").description("return_request_user_id")),
                        responseFields(
                                fieldWithPath("message").description("not_found_serial_number_exception_message")))).
        when().
                patch(baseUrl("/rents")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(400).
                body("message", is(NOT_FOUND_SERIAL_NUMBER_EXCEPTION_MESSAGE));
    }

    @DisplayName("[PATCH] /rents, 반납하는 도서가 이미 반납되었으면, 도서반납을 실패한다.")
    @Test
    void failedReturnWithAlreadyRentSerial() {
        ReturnRequestDto returnRequestDto = new ReturnRequestDto(1L, 1L);

        given(this.spec).
                body(returnRequestDto).
                accept(JSON).
                contentType(JSON).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestFields(
                                fieldWithPath("serial").description("target_serial"),
                                fieldWithPath("userId").description("return_request_user_id")),
                        responseFields(
                                fieldWithPath("returnInfo.serial").description("target_serial"),
                                fieldWithPath("returnInfo.userId").description("return_request_user_id"),
                                fieldWithPath("statusCode").description("status_code"),
                                fieldWithPath("message").description("already_returned_book_exception_message")))).
        when().
                patch(baseUrl("/rents")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(400).
                body("statusCode", is(400)).
                body("message", is(ALREADY_RETURNED_BOOK_EXCEPTION_MESSAGE));
    }
}
