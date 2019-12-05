/*
 * @(#) GithubUserApiUrlBuilder.java
 *
 * v 1.0.0
 *
 * 2019.10.10
 *
 * Copyright (c) 2019 woowacourse, thedevluffy
 * All rights reserved
 */

package com.woowacourse.tecobrary.user.infra.util;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;

@Component
public class GithubUserApiUrlBuilder {

    private static final String GITHUB_OAUTH_BASE_URL = "https://github.com/login/oauth/authorize";
    private static final String GITHUB_OAUTH_ACCESS_TOKEN_BASE_URL = "https://github.com/login/oauth/access_token";
    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String REDIRECT_URI = "redirect_uri";
    private static final String CODE = "code";
    private static final String SCOPE = "scope";
    private static final String API_INDEX_CONTROLLER = "/";

    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;
    private final String scope;

    public GithubUserApiUrlBuilder(@Value("${github.user.client_id}") String clientId,
                                   @Value("${github.user.client_secret}") String clientSecret,
                                   @Value("${github.user.redirect_uri}") String redirectUri,
                                   @Value("${github.api.scope}") String scope) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
        this.scope = scope;
    }

    public String oauth() {
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

    public String api(String code) {
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
}
