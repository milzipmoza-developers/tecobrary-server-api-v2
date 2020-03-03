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

package com.woowacourse.tecobrary.github.api;

import com.woowacourse.tecobrary.github.utils.GithubUserApiUrlBuilder;
import com.woowacourse.tecobrary.github.utils.GsonUtils;
import com.woowacourse.tecobrary.github.dto.GithubEmailDto;
import com.woowacourse.tecobrary.utils.BodyParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class GithubApi {

    private static final String ACCESS_TOKEN = "access_token";

    private final GithubUserApiUrlBuilder githubUserApiUriBuilder;
    private final GithubApiClient githubApiClient;

    @Autowired
    public GithubApi(GithubUserApiUrlBuilder githubUserApiUriBuilder,
                     GithubApiClient githubApiClient) {
        this.githubUserApiUriBuilder = githubUserApiUriBuilder;
        this.githubApiClient = githubApiClient;
    }

    public String githubUserApiAccessToken(String code) {
        String githubApiUrl = githubUserApiUriBuilder.api(code);
        String accessToken = githubApiClient.accessToken(githubApiUrl);
        Map<String, String> response = BodyParser.parse(accessToken);
        return response.get(ACCESS_TOKEN);
    }

    public String getPrimaryEmail(String githubApiUserEmailClientResponse) {
        List<GithubEmailDto> githubEmailDto = GsonUtils.parseUserEmail(githubApiUserEmailClientResponse);
        return githubEmailDto.stream()
                .filter(GithubEmailDto::isPrimary)
                .map(GithubEmailDto::getEmail)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 접근"));
    }
}
