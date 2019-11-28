package com.woowacourse.tecobrary.librarybook.ui;

import com.woowacourse.tecobrary.common.util.RestAssuredTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

class LibraryBookControllerTest extends RestAssuredTestUtils {

    @DisplayName("[POST] /books, 도서를 성공적으로 등록한다.")
    @Test
    void successfullyCreateLibraryBook() {
        given().
                contentType(JSON).
                body(new LibraryBookDto("https://image.url/book", "양파껍질학습법", "박재성", "우아한테크코스", "19700514", "이책은 사서 보는게 최고다")).
        when().
                post(baseUrl("/books")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(200).
                contentType(JSON).
                body("id", notNullValue()).
                body("message", is("양파껍질학습법 register succeed"));
    }

    @DisplayName("[POST] /books, isbn이 같은 도서가 이미 존재할 때, 등록을 실패한다.")
    @Test
    void failedCreateLibraryBook() {
        given().
                contentType(JSON).
                body(new LibraryBookDto("https://image.url/book", "양파껍질학습법", "박재성", "우아한테크코스", "0123", "이책은 사서 보는게 최고다")).
        when().
                post(baseUrl("/books")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(400).
                contentType(JSON).
                body("message", is("양파껍질학습법 register failed"));
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
}