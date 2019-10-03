/*
 * @(#) GithubUserApiUtils.java
 *
 * v 1.0.0
 *
 * 2019.10.03
 *
 * Copyright (c) 2019 woowacourse, thedevluffy
 * All rights reserved
 */

package com.woowacourse.tecobrary.user.infra.util;

import com.woowacourse.tecobrary.common.util.BodyParser;
import com.woowacourse.tecobrary.user.ui.vo.GithubEmailVo;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@Component
public class GithubUserApiUtils {

    private static final String GITHUB_OAUTH_BASE_URL = "https://github.com/login/oauth/authorize";
    private static final String GITHUB_OAUTH_ACCESS_TOKEN_BASE_URL = "https://github.com/login/oauth/access_token";
    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String REDIRECT_URI = "redirect_uri";
    private static final String CODE = "code";
    private static final String SCOPE = "scope";
    private static final String API_INDEX_CONTROLLER = "/api/v1";
    private static final String ACCESS_TOKEN = "access_token";

    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;
    private final String scope;

    public GithubUserApiUtils(@Value("${github.user.client_id}") String clientId,
                              @Value("${github.user.client_secret}") String clientSecret,
                              @Value("${github.user.redirect_uri}") String redirectUri,
                              @Value("${github.api.scope}") String scope) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.scope = scope;
    }

    public String buildOAuthUrl() {
        try {
            return new URIBuilder(GITHUB_OAUTH_BASE_URL)
                    .addParameter(CLIENT_ID, clientId)
                    .addParameter(REDIRECT_URI, redirectUri)
                    .addParameter(SCOPE, scope)
                    .build().toString();
        } catch (URISyntaxException e) {
            return API_INDEX_CONTROLLER;
        }
    }

    public String getPrimaryEmail(String githubApiUserEmailClientResponse) {
        List<GithubEmailVo> githubEmailVo = GsonUtils.parseUserEmail(githubApiUserEmailClientResponse);
        return githubEmailVo.stream()
                .filter(GithubEmailVo::isPrimary)
                .map(GithubEmailVo::getEmail)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 접근"));
    }

    public String githubUserApiAccessToken(String code) {
        String githubApiUrl = buildApiUrl(code);
        String githubApiResponse = getGithubApiResponse(githubApiUrl);
        Map<String, String> response = BodyParser.parse(githubApiResponse);
        return response.get(ACCESS_TOKEN);
    }

    private String buildApiUrl(String code) {
        try {
            return new URIBuilder(GITHUB_OAUTH_ACCESS_TOKEN_BASE_URL)
                    .addParameter(CLIENT_ID, clientId)
                    .addParameter(CLIENT_SECRET, clientSecret)
                    .addParameter(CODE, code)
                    .build().toString();
        } catch (URISyntaxException e) {
            return API_INDEX_CONTROLLER;
        }
    }

    private String getGithubApiResponse(String githubApiUrl) {
        return WebClient.create(githubApiUrl)
                .get()
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
