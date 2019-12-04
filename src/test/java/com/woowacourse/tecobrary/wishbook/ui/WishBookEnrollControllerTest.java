package com.woowacourse.tecobrary.wishbook.ui;

import com.woowacourse.tecobrary.common.util.RestAssuredTestUtils;
import com.woowacourse.tecobrary.wishbook.ui.dto.WishBookEnrollRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import static com.woowacourse.tecobrary.wishbook.command.domain.NotFoundWishBookException.NOT_FOUND_WISH_BOOK_EXCEPTION_MESSAGE;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

class WishBookEnrollControllerTest extends RestAssuredTestUtils {

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
                body("libraryBook.image", is("https://bookthumb-phinf.pstatic.net/cover/158/717/15871738.jpg?type=m1&udate=20191126")).
                body("libraryBook.title", is("스프링 부트와 AWS로 혼자 구현하는 웹 서비스 (인텔리제이, JPA, JUnit 테스트, 그레이들)")).
                body("libraryBook.author", is("이동욱")).
                body("libraryBook.publisher", is("프리렉")).
                body("libraryBook.isbn",is("8965402603 9788965402602")).
                body("libraryBook.description", is("경험이 실력이 되는 순간!이 책은 제목 그대로 스프링 부트와 AWS로 웹 서비스를 구현합니다. JPA와 " +
                        "JUNIT 테스트, 그레이들, 머스테치, 스프링 시큐리티를 활용한 소셜 로그인 등으로 애플리케이션을 개발하고, " +
                        "뒤이어 AWS 인프라의 기본 사용법과 AWS EC2와 RDS를 사용해 서비스가 가능하도록 합니다. 이렇게... ")).
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