package com.woowacourse.tecobrary.user.command.util;

import com.woowacourse.tecobrary.user.command.domain.UserGithubInfo;
import com.woowacourse.tecobrary.user.infra.dto.UserGithubInfoDto;
import com.woowacourse.tecobrary.user.ui.vo.GithubUserInfoVo;

public class UserGithubInfoMapper {

    public static UserGithubInfo toDomain(final GithubUserInfoVo githubUserInfoVo, final String primaryEmail) {
        UserGithubInfoDto githubUserInfo = UserGithubInfoDto.builder()
                .id(githubUserInfoVo.getId())
                .name(githubUserInfoVo.getName())
                .email(primaryEmail)
                .avatarUrl(githubUserInfoVo.getAvatar_url())
                .build();

        return UserGithubInfo.builder()
                .githubId(githubUserInfo.getGithubId())
                .name(githubUserInfo.getName())
                .email(githubUserInfo.getEmail())
                .httpsUrl(githubUserInfo.getAvatarUrl())
                .build();
    }
}
