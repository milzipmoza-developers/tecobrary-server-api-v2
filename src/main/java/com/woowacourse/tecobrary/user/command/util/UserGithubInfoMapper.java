package com.woowacourse.tecobrary.user.command.util;

import com.woowacourse.tecobrary.user.command.domain.UserGithubInfo;
import com.woowacourse.tecobrary.user.infra.dto.UserGithubInfoDto;
import com.woowacourse.tecobrary.user.ui.vo.GithubUserInfoVo;

public class UserGithubInfoMapper {

    public static UserGithubInfo map(GithubUserInfoVo githubUserInfoVo, String primaryEmail) {
        UserGithubInfoDto githubUserInfo = new UserGithubInfoDto(
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
