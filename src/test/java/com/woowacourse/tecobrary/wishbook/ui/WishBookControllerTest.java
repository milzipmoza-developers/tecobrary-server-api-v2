package com.woowacourse.tecobrary.wishbook.ui;

import com.woowacourse.tecobrary.common.util.RestAssuredTestUtils;
import com.woowacourse.tecobrary.wishbook.command.util.WishBookInfoDtoMapper;
import com.woowacourse.tecobrary.wishbook.common.WishBookStatic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;


class WishBookControllerTest extends RestAssuredTestUtils implements WishBookStatic {

    @DisplayName("[GET] /wishes?page=1&number=10, 해당 page에 해당하는 number 개의 wishbook 리스트를 조회한다.")
    @Test
    void successfullyFindWishBooks() {
        given().
                param("page", 1).
                param("number", 10).
                accept(JSON).
        when().
                get(baseUrl("/wishes")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(200).
                contentType(JSON).
                body("size()", is(10)).
                body("[0].image", equalTo(TEST_COVER_URL)).
                body("[0].title", equalTo(TEST_TITLE)).
                body("[0].author", equalTo(TEST_AUTHOR)).
                body("[0].isbn", equalTo(TEST_ISBN)).
                body("[0].userId", equalTo(2)).

                body("[1].image", equalTo(TEST_COVER_URL_01)).
                body("[1].title", equalTo(TEST_TITLE_01)).
                body("[1].author", equalTo(TEST_AUTHOR_01)).
                body("[1].isbn", equalTo(TEST_ISBN_01)).
                body("[1].userId", equalTo(12));
    }

    @DisplayName("[POST] /wishes, WishBook 에 책 정보를 등록한다.")
    @Test
    void successfullyCreateWishBook() {
        given().
                body(WishBookInfoDtoMapper.toDto(TEST_CREATE_WISH_BOOK)).
                contentType(JSON).
                accept(JSON).
        when().
                post(baseUrl("/wishes")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(200).
                contentType(JSON).
                body("image", equalTo(TEST_COVER_URL_01)).
                body("title", equalTo(TEST_TITLE_01)).
                body("author", equalTo(TEST_AUTHOR_01)).
                body("isbn", equalTo(TEST_CREATE_ISBN)).
                body("userId", equalTo(12));
    }

    @DisplayName("[DELETE] /wishes?id=1, 아이디1 에 해당하는 해당하는 wish book 삭제한다.")
    @DirtiesContext
    @Test
    void successfullyDeleteWishBook() {
        given().
                param("id", 1L).
        when().
                delete(baseUrl("/wishes")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(200).
                contentType(JSON).
                body("message", is("success"));
    }
}
