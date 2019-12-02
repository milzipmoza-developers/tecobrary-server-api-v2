package com.woowacourse.tecobrary.librarybook.ui;

import com.woowacourse.tecobrary.common.util.RestAssuredTestUtils;
import com.woowacourse.tecobrary.librarybook.common.LibraryBookStatic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.woowacourse.tecobrary.librarybook.exception.NotFoundLibraryBookException.NOT_FOUND_LIBRARY_BOOK_EXCEPTION_MESSAGE;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

class LibraryBookControllerTest extends RestAssuredTestUtils implements LibraryBookStatic {

    @DisplayName("[POST] /books, 도서를 성공적으로 등록한다.")
    @Test
    void successfullyCreateLibraryBook() {
        given().
                contentType(JSON).
                body(new LibraryBookRequestDto(TEST_IMAGE, TEST_TITLE, TEST_AUTHOR, TEST_PUBLISHER, TEST_ISBN, TEST_DESCRIPTION)).
        when().
                post(baseUrl("/books")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(200).
                contentType(JSON).
                body("id", notNullValue()).
                body("message", is(TEST_TITLE + " register succeed"));
    }

    @DisplayName("[POST] /books, isbn이 같은 도서가 이미 존재할 때, 등록을 실패한다.")
    @Test
    void failedCreateLibraryBook() {
        given().
                contentType(JSON).
                body(new LibraryBookRequestDto(TEST_IMAGE, TEST_TITLE, TEST_AUTHOR, TEST_PUBLISHER, "0123", TEST_DESCRIPTION)).
        when().
                post(baseUrl("/books")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(400).
                contentType(JSON).
                body("message", is(TEST_TITLE + " register failed"));
    }

    @DisplayName("[GET] /books/all, 총 도서 수를 조회한다.")
    @Test
    void readLibraryBookTotalCount() {
        given().
        when().
                get(baseUrl("/books/all")).
        then().
                log().ifError().
                statusCode(200).
                contentType(JSON).
                body("total", is(1));
    }

    @DisplayName("[GET] /books/{id}, id 에 해당하는 도서를 조회한다.")
    @Test
    void readLibraryBook() {
        given().
                pathParam("id", 1L).
        when().
                get(baseUrl("/books/{id}")).
        then().
                log().ifError().
                statusCode(200).
                contentType(JSON).
                body("id", is(SAVED_ID.intValue())).
                body("image", is(SAVED_IMAGE)).
                body("title", is(SAVED_TITLE)).
                body("author", is(SAVED_AUTHOR)).
                body("publisher", is(SAVED_PUBLISHER)).
                body("isbn", is(SAVED_ISBN)).
                body("description", is(SAVED_DESCRIPTION));
    }

    @DisplayName("[GET] /books/{id}, 해당하는 id의 도서가 존재하지 않을 때, 조회를 실패한다.")
    @Test
    void failedReadLibraryBook() {
        given().
                pathParam("id", 2L).
        when().
                get(baseUrl("/books/{id}")).
        then().
                log().ifError().
                statusCode(400).
                contentType(JSON).
                body("message", is(NOT_FOUND_LIBRARY_BOOK_EXCEPTION_MESSAGE));
    }

    @DisplayName("[GET] /books?page=1&number=10, 해당 page 에 해당하는 number 개의 도서 list 로 반환한다.")
    @Test
    void readLibraryBooks() {
        given().
                queryParam("page", "1").
                queryParam("number", "10").
        when().
                get(baseUrl("/books")).
        then().
                log().ifError().
                statusCode(200).
                contentType(JSON).
                body("size()", is(10));
    }

    @DisplayName("[GET] /books?page=a&number=b, page 에 문자를 입력하는 경우, 조회를 실패한다.")
    @Test
    void failedReadLibraryBooks() {
        given().
                queryParam("page", "a").
                queryParam("number", "b").
        when().
                get(baseUrl("/books")).
        then().
                log().ifError().
                statusCode(400);
    }

    @DisplayName("[GET] /books/search?title=제목&page=1&number=10, 제목에 맞는 도서 정보를 조회한다.")
    @Test
    void readLibraryBooksByTitle() {
        given().
                queryParam("page", "1").
                queryParam("number", "10").
                queryParam("title", "제목").
        when().
                get(baseUrl("/books/search")).
        then().
                log().ifError().
                statusCode(200).
                contentType(JSON).
                body("size()", is(10));
    }
}