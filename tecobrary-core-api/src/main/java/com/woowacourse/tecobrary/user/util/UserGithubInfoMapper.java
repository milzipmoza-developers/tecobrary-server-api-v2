package com.woowacourse.tecobrary.user.util;

import com.woowacourse.tecobrary.github.dto.GithubUserInfoDto;
import com.woowacourse.tecobrary.user.domain.UserGithubInfo;
import com.woowacourse.tecobrary.user.infra.dto.UserGithubInfoDto;

public class UserGithubInfoMapper {

    public static UserGithubInfo toDomain(final GithubUserInfoDto githubUserInfoVo, final String primaryEmail) {
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
