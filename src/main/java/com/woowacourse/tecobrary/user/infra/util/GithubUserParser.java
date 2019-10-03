package com.woowacourse.tecobrary.user.infra.util;

import com.woowacourse.tecobrary.user.command.domain.UserGithubInfo;
import com.woowacourse.tecobrary.user.infra.dto.GithubUserInfoDto;
import com.woowacourse.tecobrary.user.ui.vo.GithubUserInfoVo;

public class GithubUserParser {

    public static UserGithubInfo parse(GithubUserInfoVo githubUserInfoVo, String primaryEmail) {
        GithubUserInfoDto githubUserInfo = new GithubUserInfoDto(
                githubUserInfoVo.getId(),
                githubUserInfoVo.getName(),
                primaryEmail,
                githubUserInfoVo.getAvatar_url()
        );

        return new UserGithubInfo(
                githubUserInfo.getGithubId(),
                githubUserInfo.getName(),
                githubUserInfo.getEmail(),
                githubUserInfo.getAvatarUrl()
        );
    }
}
