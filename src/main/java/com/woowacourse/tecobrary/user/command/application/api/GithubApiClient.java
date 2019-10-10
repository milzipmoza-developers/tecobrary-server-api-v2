/*
 * @(#) GithubApiClient.java
 *
 * v 1.0.0
 *
 * 2019.10.10
 *
 * Copyright (c) 2019 woowacourse, thedevluffy
 * All rights reserved
 */

package com.woowacourse.tecobrary.user.command.application.api;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class GithubApiClient {

    private static final String GITHUB_API_BASE_URL = "https://api.github.com/user";
    private static final String EMAIL_API_ROUTE = "/emails";
    private static final String AUTHORIZATION = "Authorization";
    private static final String USER_AGENT = "User-Agent";
    private static final String AUTHORIZATION_PREFIX = "token ";
    private static final String LOGIN_APP = "Login-App";

    String userInfo(String githubApiAccessToken) {
        return buildUserInfoClient(githubApiAccessToken)
                .get()
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    String userEmail(String githubApiAccessToken) {
        return buildUserEmailClient(githubApiAccessToken)
                .get()
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    String accessToken(String githubApiUrl) {
        return WebClient.create(githubApiUrl)
                .get()
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    private WebClient buildUserInfoClient(String githubApiAccessToken) {
        return WebClient
                .builder()
                .baseUrl(GITHUB_API_BASE_URL)
                .defaultHeader(AUTHORIZATION, AUTHORIZATION_PREFIX + githubApiAccessToken)
                .defaultHeader(USER_AGENT, LOGIN_APP)
                .build();
    }

    private WebClient buildUserEmailClient(String githubApiAccessToken) {
        return WebClient
                .builder()
                .baseUrl(GITHUB_API_BASE_URL + EMAIL_API_ROUTE)
                .defaultHeader(AUTHORIZATION, AUTHORIZATION_PREFIX + githubApiAccessToken)
                .defaultHeader(USER_AGENT, LOGIN_APP)
                .build();
    }
}
