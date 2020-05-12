package librarybook.ui;

import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookRequestDto;
import common.util.AcceptanceTestUtils;
import librarybook.common.LibraryBookStatic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import static com.woowacourse.tecobrary.librarybook.exception.NotFoundLibraryBookException.NOT_FOUND_LIBRARY_BOOK_EXCEPTION_MESSAGE;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

class LibraryBookControllerTest extends AcceptanceTestUtils implements LibraryBookStatic {

    @DisplayName("[POST] /books, 도서를 등록한다.")
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
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestFields(
                                fieldWithPath("image").description("enroll_book_image"),
                                fieldWithPath("title").description("enroll_book_title"),
                                fieldWithPath("author").description("enroll_book_author"),
                                fieldWithPath("publisher").description("enroll_book_publisher"),
                                fieldWithPath("isbn").description("enroll_book_isbn"),
                                fieldWithPath("description").description("enroll_book_desc"),
                                fieldWithPath("slackBotMode.mode").description("slack_bot_notification_on")),
                        responseFields(
                                fieldWithPath("id").description("enrolled_book_id"),
                                fieldWithPath("message").description("book_title register succeed")
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

    @DisplayName("[POST] /books, 등록할 도서와 isbn이 같은 도서가 존재하면, 도서등록을 실패한다.")
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

        given(this.spec).
                contentType(JSON).
                body(libraryBookRequestDto).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestFields(
                                fieldWithPath("image").description("enroll_book_image"),
                                fieldWithPath("title").description("enroll_book_title"),
                                fieldWithPath("author").description("enroll_book_author"),
                                fieldWithPath("publisher").description("enroll_book_publisher"),
                                fieldWithPath("isbn").description("enroll_book_isbn"),
                                fieldWithPath("description").description("enroll_book_desc"),
                                fieldWithPath("slackBotMode.mode").description("slack_bot_notification")
                        ),
                        responseFields(
                                fieldWithPath("message").description("book_title register failed")
                        ))).
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
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
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

    @DisplayName("[GET] /books/{id}, 도서를 조회한다.")
    @Test
    void successfullyReadLibraryBook() {
        given(this.spec).
                pathParam("id", 1L).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
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

    @DisplayName("[GET] /books/{id}, 도서가 존재하지 않으면, 도서조회를 실패한다.")
    @Test
    void failedReadLibraryBook() {
        given(this.spec).
                pathParam("id", 1_000_000).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        pathParameters(
                                parameterWithName("id").description("book_id")),
                        responseFields(
                                fieldWithPath("message").description("not_found_library_book_exception_message")))).
        when().
                get(baseUrl("/books/{id}")).
        then().
                log().ifError().
                statusCode(400).
                contentType(JSON).
                body("message", is(NOT_FOUND_LIBRARY_BOOK_EXCEPTION_MESSAGE));
    }

    @DisplayName("[GET] /books?page=1&number=10, 도서목록을 조회한다.")
    @Test
    void successfullyReadLibraryBooks() {
        given(this.spec).
                queryParam("page", "1").
                queryParam("number", "10").
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
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

    @DisplayName("[GET] /books?page=a&number=b, 페이지와 페이지에 대한 도서 수에 문자를 입력하면, 도서목록 조회를 실패한다.")
    @Test
    void failedReadLibraryBooks() {
        given(this.spec).
                queryParam("page", "a").
                queryParam("number", "b").
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestParameters(
                                parameterWithName("page").description("page"),
                                parameterWithName("number").description("number")))).
        when().
                get(baseUrl("/books")).
        then().
                log().ifError().
                statusCode(400);
    }

    @DisplayName("[GET] /books/title/search?title=객체&page=1&number=10, 도서 제목으로 도서를 조회한다.")
    @Test
    void successfullyReadLibraryBooksByTitle() {
        given(this.spec).
                queryParam("page", "1").
                queryParam("number", "10").
                queryParam("title", "객체").
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
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
                get(baseUrl("/books/title/search")).
        then().
                log().ifError().
                statusCode(200).
                contentType(JSON).
                body("size()", is(9));
    }
}