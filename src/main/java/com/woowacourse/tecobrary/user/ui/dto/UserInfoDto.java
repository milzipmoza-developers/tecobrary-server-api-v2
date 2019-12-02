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

import lombok.*;

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
    private UserInfoDto(final String githubId, final String email, final String name, final String avatarUrl, final String authorization) {
        this.githubId = githubId;
        this.email = email;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.authorization = authorization;
    }
}
