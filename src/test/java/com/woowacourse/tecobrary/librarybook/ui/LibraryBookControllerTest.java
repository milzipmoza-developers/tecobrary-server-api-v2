package com.woowacourse.tecobrary.librarybook.ui;

import com.woowacourse.tecobrary.common.util.RestAssuredTestUtils;
import com.woowacourse.tecobrary.librarybook.common.LibraryBookStatic;
import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookRequestDto;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.woowacourse.tecobrary.librarybook.exception.NotFoundLibraryBookException.NOT_FOUND_LIBRARY_BOOK_EXCEPTION_MESSAGE;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
class LibraryBookControllerTest extends RestAssuredTestUtils implements LibraryBookStatic {

    private RequestSpecification spec;

    @BeforeEach
    void setUp(RestDocumentationContextProvider restDocumentation) {
        this.spec = new RequestSpecBuilder()
                .addFilter(documentationConfiguration(restDocumentation))
                .build();
    }

    @DisplayName("[POST] /books, 도서를 성공적으로 등록한다.")
    @DirtiesContext
    @Test
    void successfullyCreateLibraryBook() {
        LibraryBookRequestDto libraryBookRequestDto = LibraryBookRequestDto.builder()
                .image(TEST_IMAGE)
                .title(TEST_TITLE)
                .author(TEST_AUTHOR)
                .publisher(TEST_PUBLISHER)
                .isbn(TEST_ISBN)
                .description(TEST_DESCRIPTION)
                .build();

        given(this.spec).
                contentType(JSON).
                body(libraryBookRequestDto).
                filter(document("{class-name}/{method-name}",
                        requestFields(
                                fieldWithPath("image").description("enroll_book_image"),
                                fieldWithPath("title").description("enroll_book_title"),
                                fieldWithPath("author").description("enroll_book_author"),
                                fieldWithPath("publisher").description("enroll_book_publisher"),
                                fieldWithPath("isbn").description("enroll_book_isbn"),
                                fieldWithPath("description").description("enroll_book_desc")),
                        responseFields(
                                fieldWithPath("id").description("enrolled_book_id"),
                                fieldWithPath("message").description("${enrolled_book_title} register succeed")
                        ))).
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
        LibraryBookRequestDto libraryBookRequestDto = LibraryBookRequestDto.builder()
                .image(TEST_IMAGE)
                .title(TEST_TITLE)
                .author(TEST_AUTHOR)
                .publisher(TEST_PUBLISHER)
                .isbn(TEST_LIBRARY_BOOK_ISBN)
                .description(TEST_DESCRIPTION)
                .build();

        given().
                contentType(JSON).
                body(libraryBookRequestDto).
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
    void successfullyReadLibraryBookTotalCount() {
        given(this.spec).
                filter(document("{class-name}/{method-name}",
                        responseFields(
                                fieldWithPath("total").description("total_books")))).
        when().
                get(baseUrl("/books/all")).
        then().
                log().ifError().
                statusCode(200).
                contentType(JSON).
                body("total", is(95));
    }

    @DisplayName("[GET] /books/{id}, id 에 해당하는 도서를 조회한다.")
    @Test
    void successfullyReadLibraryBook() {
        given(this.spec).
                pathParam("id", 1L).
                filter(document("{class-name}/{method-name}",
                        pathParameters(
                                parameterWithName("id").description("book_id")),
                        responseFields(
                                fieldWithPath("id").description("target_book_id"),
                                fieldWithPath("image").description("target_book_image"),
                                fieldWithPath("title").description("target_book_title"),
                                fieldWithPath("author").description("target_book_author"),
                                fieldWithPath("publisher").description("target_book_publisher"),
                                fieldWithPath("isbn").description("target_book_isbn"),
                                fieldWithPath("description").description("target_book_desc")))).
        when().
                get(baseUrl("/books/{id}")).
        then().
                log().ifError().
                statusCode(200).
                contentType(JSON).
                body("id", is(TEST_LIBRARY_BOOK_ID.intValue())).
                body("image", is(TEST_LIBRARY_BOOK_IMAGE)).
                body("title", is(TEST_LIBRARY_BOOK_TITLE)).
                body("author", is(TEST_LIBRARY_BOOK_AUTHOR)).
                body("publisher", is(TEST_LIBRARY_BOOK_PUBLISHER)).
                body("isbn", is(TEST_LIBRARY_BOOK_ISBN)).
                body("description", is(TEST_LIBRARY_BOOK_DESCRIPTION));
    }

    @DisplayName("[GET] /books/{id}, 해당하는 id의 도서가 존재하지 않을 때, Bad Request 응답을 받는다.")
    @Test
    void failedReadLibraryBook() {
        given().
                pathParam("id", 1_000_000).
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
    void successfullyReadLibraryBooks() {
        given(this.spec).
                queryParam("page", "1").
                queryParam("number", "10").
                filter(document("{class-name}/{method-name}",
                        requestParameters(
                                parameterWithName("page").description("page"),
                                parameterWithName("number").description("number")),
                        responseFields(
                                fieldWithPath("[0].id").description("target_book_id"),
                                fieldWithPath("[0].image").description("target_book_image"),
                                fieldWithPath("[0].title").description("target_book_title"),
                                fieldWithPath("[0].author").description("target_book_author"),
                                fieldWithPath("[0].publisher").description("target_book_publisher"),
                                fieldWithPath("[0].isbn").description("target_book_isbn"),
                                fieldWithPath("[0].description").description("target_book_desc")))).
        when().
                get(baseUrl("/books")).
        then().
                log().ifError().
                statusCode(200).
                contentType(JSON).
                body("size()", is(10));
    }

    @DisplayName("[GET] /books?page=a&number=b, page 에 문자를 입력하는 경우, Bad Request 응답을 받는다.")
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

    @DisplayName("[GET] /books/search?title=객체&page=1&number=10, 제목에 맞는 도서 정보를 조회한다. 테스트 DB 에서 객체를 검색하면 9개가 나온다.")
    @Test
    void successfullyReadLibraryBooksByTitle() {
        given(this.spec).
                queryParam("page", "1").
                queryParam("number", "10").
                queryParam("title", "객체").
                filter(document("{class-name}/{method-name}",
                        requestParameters(
                                parameterWithName("title").description("book_title"),
                                parameterWithName("page").description("page"),
                                parameterWithName("number").description("number")),
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
                body("size()", is(9));
    }
}