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

    private final GithubApi githubApi;
    private final GithubApiClient githubApiClient;

    @Autowired
    public GithubApiService(GithubApi githubApi,
                            GithubApiClient githubApiClient) {
        this.githubApi = githubApi;
        this.githubApiClient = githubApiClient;
    }

    public String getGithubAccessToken(String code) {
        return githubApi.githubUserApiAccessToken(code);
    }

    public GithubUserInfoVo getGithubUserInfo(String githubApiAccessToken) {
        return GsonUtils.parseUserInfo(
                githubApiClient.userInfo(githubApiAccessToken));
    }

    public String getGithubUserEmail(String githubApiAccessToken) {
        return githubApi.getPrimaryEmail(
                githubApiClient.userEmail(githubApiAccessToken));
    }
}
