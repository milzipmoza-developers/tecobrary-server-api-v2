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

import com.woowacourse.tecobrary.common.util.RestAssuredTestUtils;
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

public class RentReturnControllerTest extends RestAssuredTestUtils implements LibraryBookStatic {

    @DisplayName("[POST] /rents, serial 과 userId 를 이용해 대여를 성공적으로 처리한다.")
    @DirtiesContext
    @Test
    void successfullyRentABook() {
        RentRequestDto rentRequestDto = new RentRequestDto(1L, 1L);

        given().
                body(rentRequestDto).
                accept(JSON).
                contentType(JSON).
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

    @DisplayName("[POST] /rents, 존재하지 않는 user 에 대해서는 Bad Request 에러가 발생한다.")
    @Test
    void failedRentWithNotExistUser() {
        given().
                accept(JSON).
                contentType(JSON).
                body(new RentRequestDto(1L, 1_000_000L)).
        when().
                post(baseUrl("/rents")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(400).
                body("message", is(NOT_FOUND_USER_EXCEPTION_MESSAGE));
    }

    @DisplayName("[POST] /rents, 존재하지 않는 serial 에 대해서는 Bad Request 에러가 발생한다.")
    @Test
    void failedRentWithNotExistSerial() {
        given().
                accept(JSON).
                contentType(JSON).
                body(new RentRequestDto(1_000_000L, 1L)).
        when().
                post(baseUrl("/rents")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(400).
                body("message", is(NOT_FOUND_SERIAL_NUMBER_EXCEPTION_MESSAGE));
    }

    @DisplayName("[POST] /rents, 이미 대여 중인 serial 에 대해서는 Bad Request 에러가 발생한다.")
    @Test
    void failedRentWithAlreadyRentSerial() {
        RentRequestDto rentRequestDto = new RentRequestDto(5L, 1L);

        given().
                body(rentRequestDto).
                accept(JSON).
                contentType(JSON).
        when().
                post(baseUrl("/rents")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(400).
                body("rentInfo.serial", is(5)).
                body("message", is(ALREADY_RENT_BOOK_EXCEPTION_MESSAGE));
    }

    @DisplayName("[PATCH] /rents, serial 를 이용해 반납을 성공적으로 처리한다.")
    @DirtiesContext
    @Test
    void successfullyReturnABook() {
        RentRequestDto rentRequestDto = new RentRequestDto(131L, 21L);

        given().
                body(rentRequestDto).
                accept(JSON).
                contentType(JSON).
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

    @DisplayName("[PATCH] /rents, 존재하지 않는 user 에 대해서는 Bad Request 에러가 발생한다.")
    @Test
    void failedReturnWithNotExistUser() {
        given().
                accept(JSON).
                contentType(JSON).
                body(new ReturnRequestDto(2L, 1_000_000L)).
        when().
                patch(baseUrl("/rents")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(400).
                body("message", is(NOT_FOUND_USER_EXCEPTION_MESSAGE));
    }

    @DisplayName("[PATCH] /rents, 대여한 user 와 일치하지 않는 user 에 대해서는 Bad Request 에러가 발생한다.")
    @Test
    void failedReturnWithNotRentUser() {
        given().
                accept(JSON).
                contentType(JSON).
                body(new ReturnRequestDto(131L, 4L)).
        when().
                patch(baseUrl("/rents")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(403).
                body("statusCode", is(403)).
                body("message", is(NOT_PERMITTED_USER_EXCEPTION_MESSAGE));
    }

    @DisplayName("[PATCH] /rents, 존재하지 않는 serial 에 대해서는 Bad Request 에러가 발생한다.")
    @Test
    void failedReturnWithNotExistSerial() {
        given().
                accept(JSON).
                contentType(JSON).
                body(new ReturnRequestDto(1_000_000L, 1L)).
        when().
                patch(baseUrl("/rents")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(400).
                body("message", is(NOT_FOUND_SERIAL_NUMBER_EXCEPTION_MESSAGE));
    }

    @DisplayName("[PATCH] /rents, 이미 반납된 serial 에 대해서는 Bad Request 에러가 발생한다.")
    @Test
    void failedReturnWithAlreadyRentSerial() {
        ReturnRequestDto returnRequestDto = new ReturnRequestDto(1L, 1L);

        given().
                body(returnRequestDto).
                accept(JSON).
                contentType(JSON).
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
