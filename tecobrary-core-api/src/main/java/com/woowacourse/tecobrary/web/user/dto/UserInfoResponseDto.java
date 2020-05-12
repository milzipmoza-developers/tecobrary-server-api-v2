package com.woowacourse.tecobrary.web.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInfoResponseDto {

    private final String githubId;
    private final String email;
    private final String name;
    private final String avatarUrl;
    private final String authorization;

    @Builder
    public UserInfoResponseDto(String githubId, String email, String name, String avatarUrl, String authorization) {
        this.githubId = githubId;
        this.email = email;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.authorization = authorization;
    }
}
