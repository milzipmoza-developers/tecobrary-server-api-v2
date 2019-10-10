/*
 * @(#) GithubApiStatic.java
 *
 * v 1.0.0
 *
 * 2019.10.10
 *
 * Copyright (c) 2019 woowacourse, thedevluffy
 * All rights reserved
 */

package com.woowacourse.tecobrary.user.common;

public interface GithubApiStatic {

    String GITHUB_OAUTH_BASE_URL = "https://github.com/login/oauth/authorize";
    String GITHUB_OAUTH_ACCESS_TOKEN_BASE_URL = "https://github.com/login/oauth/access_token";
    String CLIENT_ID_KEY = "client_id";
    String CLIENT_ID_VALUE = "cccccllllliiiiieeeeenntttId";
    String CLIENT_SECRET_KEY = "client_secret";
    String CLIENT_SECRET_VALUE = "ssseeeecccrrreeetttt";
    String REDIRECT_URI_KEY = "redirect_uri";
    String REDIRECT_URI_VALUE = "http://localhost:8080/auth";
    String SCOPE_KEY = "scope";
    String SCOPE_VALUE = "user";
    String CODE_KEY = "code";
    String CODE_VALUE = "fake_code";
}
