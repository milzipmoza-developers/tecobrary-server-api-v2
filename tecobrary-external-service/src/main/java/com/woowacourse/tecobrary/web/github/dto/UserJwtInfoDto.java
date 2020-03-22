/*
 * @(#) UserJwtInfoVo.java
 *
 * v 1.0.0
 *
 * 2019.11.29
 *
 * Copyright (c) 2019 woowacourse, thedevluffy
 * All rights reserved
 */

package com.woowacourse.tecobrary.web.github.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class UserJwtInfoDto {

    private String id;
    private String email;
    private String name;
    private String avatarUrl;
    private String authorization;

    @Builder
    private UserJwtInfoDto(final Long id, final String email, final String name, final String avatarUrl, final String authorization) {
        this.id = String.valueOf(id);
        this.email = email;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.authorization = authorization;
    }
}
