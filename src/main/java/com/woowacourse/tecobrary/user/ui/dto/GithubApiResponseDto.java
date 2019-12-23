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

package com.woowacourse.tecobrary.user.ui.dto;

import com.woowacourse.tecobrary.user.ui.vo.UserJwtInfoVo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class GithubApiResponseDto {

    private UserJwtInfoVo user;
    private String token;

    public GithubApiResponseDto(UserJwtInfoVo user, String token) {
        this.user = user;
        this.token = token;
    }
}
