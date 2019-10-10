package com.woowacourse.tecobrary.user.command.application.api;

import com.woowacourse.tecobrary.user.infra.util.GithubApiRequestClient;
import com.woowacourse.tecobrary.user.infra.util.GithubUserApiUtils;
import com.woowacourse.tecobrary.user.infra.util.GsonUtils;
import com.woowacourse.tecobrary.user.ui.vo.GithubUserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GithubApiService {

    private final GithubUserApiUtils githubUserApiUtils;
    private final GithubApiRequestClient githubApiRequestClient;

    @Autowired
    public GithubApiService(GithubUserApiUtils githubUserApiUtils,
                            GithubApiRequestClient githubApiRequestClient) {
        this.githubUserApiUtils = githubUserApiUtils;
        this.githubApiRequestClient = githubApiRequestClient;
    }

    public String getGithubAccessToken(String code) {
        return githubUserApiUtils.githubUserApiAccessToken(code);
    }

    public GithubUserInfoVo githubUserInfo(String githubApiAccessToken) {
        return GsonUtils.parseUserInfo(
                githubApiRequestClient.userInfo(githubApiAccessToken));
    }

    public String githubUserEmail(String githubApiAccessToken) {
        return githubUserApiUtils.getPrimaryEmail(
                githubApiRequestClient.userEmail(githubApiAccessToken));
    }
}
