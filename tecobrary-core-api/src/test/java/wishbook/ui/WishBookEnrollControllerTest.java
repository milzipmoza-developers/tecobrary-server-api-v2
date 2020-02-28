package wishbook.ui;

import com.woowacourse.tecobrary.wishbook.ui.dto.WishBookEnrollRequestDto;
import common.util.AcceptanceTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;
import wishbook.common.WishBookStatic;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

class WishBookEnrollControllerTest extends AcceptanceTestUtils implements WishBookStatic {

    private static final String NOT_FOUND_WISH_BOOK_EXCEPTION_MESSAGE = "존재하지 않는 희망도서 입니다.";

    @DisplayName("[PATCH] /wishes, 구입된 희망도서를 도서목록에 등록한다.")
    @DirtiesContext
    @Test
    public void successfullyEnroll() {
        WishBookEnrollRequestDto wishBookEnrollRequestDto = new WishBookEnrollRequestDto(23L);
        
        given(this.spec).
                body(wishBookEnrollRequestDto).
                accept(JSON).
                contentType(JSON).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestFields(
                                fieldWithPath("id").description("target_wishbook_id")),
                        responseFields(
                                fieldWithPath("libraryBook.id").description("enroll_book_id"),
                                fieldWithPath("libraryBook.image").description("enroll_book_image"),
                                fieldWithPath("libraryBook.title").description("enroll_book_title"),
                                fieldWithPath("libraryBook.author").description("enroll_book_author"),
                                fieldWithPath("libraryBook.publisher").description("enroll_book_publisher"),
                                fieldWithPath("libraryBook.isbn").description("enroll_book_isbn"),
                                fieldWithPath("libraryBook.description").description("enroll_book_desc"),
                                fieldWithPath("libraryBook.enrolledDate").description("library_book_create_time"),
                                fieldWithPath("enrolledDate").description("library_book_create_time")
                        ))).
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

    @DisplayName("[PATCH] /wishes, 희망도서에 등록되어 이미 구입이 완료된 도서를 도서목록에 등록하면, 도서목록 등록을 실패한다.")
    @Test
    public void failedEnrollAlreadySoftDeleted() {
        WishBookEnrollRequestDto wishBookEnrollRequestDto = new WishBookEnrollRequestDto(12L);

        given(this.spec).
                body(wishBookEnrollRequestDto).
                accept(JSON).
                contentType(JSON).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestFields(
                                fieldWithPath("id").description("target_wishbook_id")),
                        responseFields(
                                fieldWithPath("message").description("not_found_wish_book_exception_message")))).
        when().
                patch(baseUrl("/wishes")).
        then().
                log().ifError().
                log().ifValidationFails().
                contentType(JSON).
                statusCode(400).
                body("message", is(NOT_FOUND_WISH_BOOK_EXCEPTION_MESSAGE));
    }

    @DisplayName("[PATCH] /wishes, 희망도서를 도서목록에 등록할 때 희망도서가 희망 도서목록에 존재하지 않으면, 도서목록 등록을 실패한다.")
    @Test
    public void failedEnrollNotFoundWishBookId() {
        WishBookEnrollRequestDto wishBookEnrollRequestDto = new WishBookEnrollRequestDto(1_000_000L);

        given(this.spec).
                body(wishBookEnrollRequestDto).
                accept(JSON).
                contentType(JSON).
                filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                        requestFields(
                                fieldWithPath("id").description("target_wishbook_id")),
                        responseFields(
                                fieldWithPath("message").description("not_found_wish_book_exception_message")))).
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