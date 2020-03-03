package com.woowacourse.tecobrary.user.utils;

import com.woowacourse.tecobrary.github.dto.GithubUserInfoDto;
import com.woowacourse.tecobrary.user.domain.UserGithubInfo;
import com.woowacourse.tecobrary.github.dto.UserGithubInfoDto;

public class UserGithubInfoMapper {

    public static UserGithubInfo toDomain(final GithubUserInfoDto githubUserInfoDto, final String primaryEmail) {
        UserGithubInfoDto githubUserInfo = UserGithubInfoDto.builder()
                .name(githubUserInfoDto.getName())
                .email(primaryEmail)
                .avatarUrl(githubUserInfoDto.getAvatar_url())
                .build();

        return UserGithubInfo.builder()
                .githubId(githubUserInfo.getGithubId())
                .name(githubUserInfo.getName())
                .email(githubUserInfo.getEmail())
                .httpsUrl(githubUserInfo.getAvatarUrl())
                .build();
    }
}
