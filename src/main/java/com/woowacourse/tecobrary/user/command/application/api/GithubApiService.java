/*
 * @(#) GithubApiService.java
 *
 * v 1.0.0
 *
 * 2019.10.10
 *
 * Copyright (c) 2019 woowacourse, thedevluffy
 * All rights reserved
 */

package com.woowacourse.tecobrary.user.command.application.api;

import com.woowacourse.tecobrary.user.infra.util.GsonUtils;
import com.woowacourse.tecobrary.user.ui.vo.GithubUserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GithubApiService {

    private final GithubUserApi githubUserApi;
    private final GithubApiClient githubApiClient;

    @Autowired
    public GithubApiService(GithubUserApi githubUserApi,
                            GithubApiClient githubApiClient) {
        this.githubUserApi = githubUserApi;
        this.githubApiClient = githubApiClient;
    }

    public String getGithubAccessToken(String code) {
        return githubUserApi.githubUserApiAccessToken(code);
    }

    public GithubUserInfoVo githubUserInfo(String githubApiAccessToken) {
        return GsonUtils.parseUserInfo(
                githubApiClient.userInfo(githubApiAccessToken));
    }

    public String githubUserEmail(String githubApiAccessToken) {
        return githubUserApi.getPrimaryEmail(
                githubApiClient.userEmail(githubApiAccessToken));
    }
}
