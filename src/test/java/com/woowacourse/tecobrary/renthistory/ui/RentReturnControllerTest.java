package com.woowacourse.tecobrary.renthistory.ui;

import com.woowacourse.tecobrary.common.util.RestAssuredTestUtils;
import com.woowacourse.tecobrary.librarybook.common.LibraryBookStatic;
import com.woowacourse.tecobrary.renthistory.ui.dto.RentRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import static com.woowacourse.tecobrary.renthistory.application.AlreadyRentBookException.ALREADY_RENT_BOOK_EXCEPTION_MESSAGE;
import static com.woowacourse.tecobrary.serial.exception.NotFoundSerialNumberException.NOT_FOUND_SERIAL_NUMBER_EXCEPTION_MESSAGE;
import static com.woowacourse.tecobrary.user.command.application.NotFoundUserException.NOT_FOUND_USER_EXCEPTION_MESSAGE;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;

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
                body("message", is("대여에 성공하였습니다."));
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
}
