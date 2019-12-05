package com.woowacourse.tecobrary.user.ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GithubOAuthControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @DisplayName("[GET] /github/user/oauth 로 요청시 성공적으로 리다이렉트 된다.")
    @Test
    void githubConfirmAuthentication() {
        webTestClient.method(HttpMethod.GET)
                .uri("/github/user/oauth")
                .exchange()
                .expectStatus().isFound();
    }
}