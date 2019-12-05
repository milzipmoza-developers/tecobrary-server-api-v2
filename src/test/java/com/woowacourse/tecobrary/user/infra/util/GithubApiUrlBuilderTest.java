/*
 * @(#) GithubUserApiUrlBuilderTest.java
 *
 * v 1.0.0
 *
 * 2019.10.10
 *
 * Copyright (c) 2019 woowacourse, thedevluffy
 * All rights reserved
 */
package com.woowacourse.tecobrary.user.infra.util;

import com.woowacourse.tecobrary.user.common.GithubApiStatic;
import org.apache.http.client.utils.URIBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class GithubApiUrlBuilderTest implements GithubApiStatic {

    private GithubUserApiUrlBuilder githubUserApiUrlBuilder;

    @BeforeEach
    void setUp() {
        githubUserApiUrlBuilder = new GithubUserApiUrlBuilder(
                CLIENT_ID_VALUE,
                CLIENT_SECRET_VALUE,
                REDIRECT_URI_VALUE,
                SCOPE_VALUE
        );
    }

    @DisplayName("oauth 메서드가 정상적으로 동작한다.")
    @Test
    void oauth() throws URISyntaxException {
        assertEquals(
                githubUserApiUrlBuilder.oauth(),
                new URIBuilder(GITHUB_OAUTH_BASE_URL)
                        .addParameter(CLIENT_ID_KEY, CLIENT_ID_VALUE)
                        .addParameter(REDIRECT_URI_KEY, REDIRECT_URI_VALUE)
                        .addParameter(SCOPE_KEY, SCOPE_VALUE)
                        .build().toString()
        );
    }

    @DisplayName("api 메서드가 정상적으로 동작한다.")
    @Test
    void api() throws URISyntaxException {
        assertEquals(
                githubUserApiUrlBuilder.api(CODE_VALUE),
                new URIBuilder(GITHUB_OAUTH_ACCESS_TOKEN_BASE_URL)
                        .addParameter(CLIENT_ID_KEY, CLIENT_ID_VALUE)
                        .addParameter(CLIENT_SECRET_KEY, CLIENT_SECRET_VALUE)
                        .addParameter(CODE_KEY, CODE_VALUE)
                        .build().toString()
        );
    }

}