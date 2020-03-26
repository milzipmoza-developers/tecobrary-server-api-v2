package wishbook.ui;

import com.woowacourse.tecobrary.wishbook.util.WishBookInfoDtoMapper;
import common.util.AcceptanceTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;
import wishbook.common.WishBookStatic;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

class WishBookControllerTest extends AcceptanceTestUtils implements WishBookStatic {
    private static final String DUPLICATED_WISH_BOOK_ISBN_EXCEPTION_MESSAGE = "이미 희망도서에 등록되어 있습니다.";

    @DisplayName("[GET] /wishes?page=1&number=10, 희망도서 목록에서 도서를 조회한다.")
    @Test
    void successfullyFindWishBooks() {
        given(this.spec).
                param("page", 1).
                param("number", 10).
                accept(JSON).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestParameters(
                                parameterWithName("page").description("page"),
                                parameterWithName("number").description("number")),
                        responseFields(
                                fieldWithPath("[0].image").description("target_book_image"),
                                fieldWithPath("[0].title").description("target_book_title"),
                                fieldWithPath("[0].author").description("target_book_author"),
                                fieldWithPath("[0].publisher").description("target_book_publisher"),
                                fieldWithPath("[0].isbn").description("target_book_isbn"),
                                fieldWithPath("[0].description").description("target_book_desc"),
                                fieldWithPath("[0].userId").description("who_request_wishbook")
                                ))).
        when().
                get(baseUrl("/wishes")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(200).
                contentType(JSON).
                body("size()", is(1)).
                body("[0].image", equalTo(TEST_COVER_URL_02)).
                body("[0].title", equalTo(TEST_TITLE_02)).
                body("[0].author", equalTo(TEST_AUTHOR_02)).
                body("[0].isbn", equalTo(TEST_ISBN_02));
    }

    @DisplayName("[POST] /wishes, 희망도서 목록에 도서를 등록한다.")
    @Test
    void successfullyCreateWishBook() {
        given(this.spec).
                body(WishBookInfoDtoMapper.toDto(TEST_CREATE_WISH_BOOK)).
                contentType(JSON).
                accept(JSON).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestFields(
                                fieldWithPath("userId").description("who_request_wishbook"),
                                fieldWithPath("image").description("wish_book_image"),
                                fieldWithPath("title").description("wish_book_title"),
                                fieldWithPath("author").description("wish_book_author"),
                                fieldWithPath("publisher").description("wish_book_publisher"),
                                fieldWithPath("isbn").description("wish_book_isbn"),
                                fieldWithPath("description").description("wish_book_desc")),
                        responseFields(
                                fieldWithPath("userId").description("who_request_wishbook"),
                                fieldWithPath("image").description("enroll_book_image"),
                                fieldWithPath("title").description("enroll_book_title"),
                                fieldWithPath("author").description("enroll_book_author"),
                                fieldWithPath("publisher").description("enroll_book_publisher"),
                                fieldWithPath("isbn").description("enroll_book_isbn"),
                                fieldWithPath("description").description("enroll_book_desc")
                        ))).
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

    @DisplayName("[POST] /wishes, 요청한 희망도서가 희망도서 목록에 존재하면, 희망도서 등록을 실패한다.")
    @Test
    void failedCreatedWishBook() {
        given(this.spec).
                body(WishBookInfoDtoMapper.toDto(TEST_WISH_BOOK_02)).
                contentType(JSON).
                accept(JSON).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestFields(
                                fieldWithPath("userId").description("who_request_wishbook"),
                                fieldWithPath("image").description("wish_book_image"),
                                fieldWithPath("title").description("wish_book_title"),
                                fieldWithPath("author").description("wish_book_author"),
                                fieldWithPath("publisher").description("wish_book_publisher"),
                                fieldWithPath("isbn").description("wish_book_isbn"),
                                fieldWithPath("description").description("wish_book_desc")),
                        responseFields(
                                fieldWithPath("message").description("duplicate_wish_book_isbn_exception_message")))).
        when().
                post(baseUrl("/wishes")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(400).
                contentType(JSON).
                body("message", is(DUPLICATED_WISH_BOOK_ISBN_EXCEPTION_MESSAGE));
    }

    @DisplayName("[DELETE] /wishes?id=1, 희망도서 목록에서 희망도서를 삭제한다.")
    @DirtiesContext
    @Test
    void successfullyDeleteWishBook() {
        given(this.spec).
                param("id", 1L).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestParameters(
                                parameterWithName("id").description("wish_book_id")),
                        responseFields(
                                fieldWithPath("message").description("success")))).
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
