package com.woowacourse.tecobrary.common.naverapi.ui;

import com.woowacourse.tecobrary.common.util.AcceptanceTestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

class NaverApiControllerTest extends AcceptanceTestUtils {

    @DisplayName("[GET] /naverapi, 검색 키워드와 갯수, 페이지를 요청시 성공적으로 응답을 받는다.")
    @Test
    void successfullySearchFromNaverApi() {
            given(this.spec).
                    queryParam("keyword", "객체지향").
                    queryParam("page", "1").
                    queryParam("number", "10").
                    filter(document(DOCUMENTATION_OUTPUT_DIRECTORY,
                            requestParameters(
                                    parameterWithName("keyword").description("naver 검색 api 에 전달할 검색어"),
                                    parameterWithName("page").description("naver 검색 api 에 전달할 페이지"),
                                    parameterWithName("number").description("naver 검색 api 에 전달할 한 페이지의 아이템 수")
                            ),
                            responseFields(
                                    fieldWithPath("[0].title").description("검색 결과의 도서 제목"),
                                    fieldWithPath("[0].link").description("검색 결과의 도서 네이버 링크 (불필요)"),
                                    fieldWithPath("[0].image").description("검색 결과의 도서 커버 이미지 URL"),
                                    fieldWithPath("[0].author").description("검색 결과의 도서 저자"),
                                    fieldWithPath("[0].price").description("검색 결과의 도서 가격 (불필요)"),
                                    fieldWithPath("[0].discount").description("검색 결과의 도서 할일 가격 (불필요)"),
                                    fieldWithPath("[0].publisher").description("검색 결과의 도서 출판사"),
                                    fieldWithPath("[0].pubDate").description("검색 결과의 도서 출판일 (불필요)"),
                                    fieldWithPath("[0].isbn").description("검색 결과의 도서 ISBN"),
                                    fieldWithPath("[0].description").description("검색 결과의 도서 요약")
                            )
                    )).
            when().
                    get(baseUrl("/naverapi")).
            then().
                    log().ifError().
                    log().ifValidationFails().
                    statusCode(200).
                    body("[0].title", is(notNullValue())).
                    body("[0].link", is(anything())).
                    body("[0].image", is(notNullValue())).
                    body("[0].author", is(notNullValue())).
                    body("[0].price", is(anything())).
                    body("[0].discount", is(anything())).
                    body("[0].publisher", is(anything())).
                    body("[0].pubDate", is(anything())).
                    body("[0].isbn", is(notNullValue())).
                    body("[0].description", is(notNullValue()));
    }
}