package com.woowacourse.tecobrary.renthistory.ui;

import com.woowacourse.tecobrary.common.util.RestAssuredTestUtils;
import com.woowacourse.tecobrary.librarybook.common.LibraryBookStatic;
import com.woowacourse.tecobrary.renthistory.common.RentHistoryStatic;
import com.woowacourse.tecobrary.serial.common.SerialStatic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.equalTo;

class RentHistoryReadControllerTest extends RestAssuredTestUtils implements RentHistoryStatic, LibraryBookStatic, SerialStatic {

    @DisplayName("[GET] /rents/:userId, 해당 유저의 전체 도서 대여 목록을 반환한다.")
    @Test
    void successfullyFindAllRentHistoryByUser() {

        given().
                accept(JSON).
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
}