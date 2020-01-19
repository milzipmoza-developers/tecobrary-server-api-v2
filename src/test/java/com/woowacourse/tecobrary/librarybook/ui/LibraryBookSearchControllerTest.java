package com.woowacourse.tecobrary.librarybook.ui;

import com.woowacourse.tecobrary.common.util.ESAcceptanceTestUtils;
import com.woowacourse.tecobrary.librarybook.common.LibraryBookStatic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

class LibraryBookSearchControllerTest extends ESAcceptanceTestUtils implements LibraryBookStatic {

    @DisplayName("[GET] /books/search?keyword=카카오&page=1&size=10, 키워드로 도서를 조회한다.")
    @Test
    void successfullySearchKeyword() {
        given(this.spec).
                queryParam("keyword", "카카오").
                queryParam("page", "1").
                queryParam("size", "10").
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestParameters(
                                parameterWithName("keyword").description("keyword"),
                                parameterWithName("page").description("page"),
                                parameterWithName("size").description("size")),
                        responseFields(
                                fieldWithPath("[0].id").description("target_book_id"),
                                fieldWithPath("[0].image").description("target_book_image"),
                                fieldWithPath("[0].title").description("target_book_title"),
                                fieldWithPath("[0].author").description("target_book_author"),
                                fieldWithPath("[0].publisher").description("target_book_publisher"),
                                fieldWithPath("[0].isbn").description("target_book_isbn"),
                                fieldWithPath("[0].description").description("target_book_desc")))).
        when().
                get(baseUrl("/books/search")).
        then().
                log().ifError().
                statusCode(200).
                contentType(JSON).
                body("size()", notNullValue()).
                body("[0].id", equalTo(TEST_LIBRARY_BOOK_ID_16.intValue())).
                body("[0].image", equalTo(TEST_LIBRARY_BOOK_IMAGE_16)).
                body("[0].title", equalTo(TEST_LIBRARY_BOOK_TITLE_16)).
                body("[0].author", containsString(TEST_LIBRARY_BOOK_AUTHOR_16)).
                body("[0].isbn", equalTo(TEST_LIBRARY_BOOK_ISBN_16));
    }
}