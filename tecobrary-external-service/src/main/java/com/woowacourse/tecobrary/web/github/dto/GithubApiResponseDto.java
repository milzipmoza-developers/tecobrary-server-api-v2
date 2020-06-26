/*
 * @(#) GithubApiResponseDto.java
 *
 * v 1.0.0
 *
 * 2019.11.29
 *
 * Copyright (c) 2019 woowacourse, thedevluffy
 * All rights reserved
 */

package com.woowacourse.tecobrary.web.github.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class GithubApiResponseDto {

    private UserJwtInfoDto user;
    private String token;

    @Builder
    public GithubApiResponseDto(UserJwtInfoDto user, String token) {
        this.user = user;
        this.token = token;
    }
}
