package com.woowacourse.tecobrary.web.github.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class UserGithubInfoDto {

    private String githubId;
    private String name;
    private String email;
    private String avatarUrl;

    @Builder
    public UserGithubInfoDto(String githubId, String name, String email, String avatarUrl) {
        this.githubId = githubId;
        this.name = name;
        this.email = email;
        this.avatarUrl = avatarUrl;
    }
}
