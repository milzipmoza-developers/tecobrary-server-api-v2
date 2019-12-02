/*
 * @(#) UserInfoDto.java
 *
 * v 1.0.0
 *
 * 2019.12.02
 *
 * Copyright (c) 2019 woowacourse, thedevluffy, gch01410, LeeYounghyeon
 * All rights reserved
 */

package com.woowacourse.tecobrary.user.ui.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class UserInfoDto {

    private String githubId;
    private String email;
    private String name;
    private String avatarUrl;
    private String authorization;

    @Builder
    public UserInfoDto(String githubId, String email, String name, String avatarUrl, String authorization) {
        this.githubId = githubId;
        this.email = email;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.authorization = authorization;
    }
}
