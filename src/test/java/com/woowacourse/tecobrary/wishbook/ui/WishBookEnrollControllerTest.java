package com.woowacourse.tecobrary.wishbook.ui;

import com.woowacourse.tecobrary.common.util.RestAssuredTestUtils;
import com.woowacourse.tecobrary.wishbook.common.WishBookStatic;
import com.woowacourse.tecobrary.wishbook.ui.dto.WishBookEnrollRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import static com.woowacourse.tecobrary.wishbook.command.domain.NotFoundWishBookException.NOT_FOUND_WISH_BOOK_EXCEPTION_MESSAGE;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

class WishBookEnrollControllerTest extends RestAssuredTestUtils implements WishBookStatic {

    @DisplayName("[PATCH] /wishes, wish book 의 id 로 wish book 에서 soft delete 한 후 library book 에 등록을 성공한다.")
    @DirtiesContext
    @Test
    public void successfullyEnroll() {
        WishBookEnrollRequestDto wishBookEnrollRequestDto = new WishBookEnrollRequestDto(23L);
        
        given().
                body(wishBookEnrollRequestDto).
                accept(JSON).
                contentType(JSON).
        when().
                patch(baseUrl("/wishes")).
        then().
                log().ifError().
                log().ifValidationFails().
                contentType(JSON).
                statusCode(200).
                body("libraryBook.image", is(TEST_COVER_URL_02)).
                body("libraryBook.title", is(TEST_TITLE_02)).
                body("libraryBook.author", is(TEST_AUTHOR_02)).
                body("libraryBook.publisher", is(TEST_PUBLISHER_02)).
                body("libraryBook.isbn",is(TEST_ISBN_02)).
                body("libraryBook.description", is(TEST_DESCRIPTION_02)).
                body("enrolledDate", notNullValue());
    }

    @DisplayName("[PATCH] /wishes, 이미 soft delete 된 wish book 의 id 로 요청시 NotFoundWishBookException, Bad Request 를 응답 받는다.")
    @Test
    public void failedEnrollAlreadySoftDeleted() {
        WishBookEnrollRequestDto wishBookEnrollRequestDto = new WishBookEnrollRequestDto(12L);

        given().
                body(wishBookEnrollRequestDto).
                accept(JSON).
                contentType(JSON).
        when().
                patch(baseUrl("/wishes")).
        then().
                log().ifError().
                log().ifValidationFails().
                contentType(JSON).
                statusCode(400).
                body("message", is(NOT_FOUND_WISH_BOOK_EXCEPTION_MESSAGE));
    }

    @DisplayName("[PATCH] /wishes, 존재하지 않는 id 로 요청시 NotFoundWishBookException, Bad Request 를 응답 받는다.")
    @Test
    public void failedEnrollNotFoundWishBookId() {
        WishBookEnrollRequestDto wishBookEnrollRequestDto = new WishBookEnrollRequestDto(1_000_000L);

        given().
                body(wishBookEnrollRequestDto).
                accept(JSON).
                contentType(JSON).
        when().
                patch(baseUrl("/wishes")).
        then().
                log().ifError().
                log().ifValidationFails().
                contentType(JSON).
                statusCode(400).
                body("message", is(NOT_FOUND_WISH_BOOK_EXCEPTION_MESSAGE));
    }
}