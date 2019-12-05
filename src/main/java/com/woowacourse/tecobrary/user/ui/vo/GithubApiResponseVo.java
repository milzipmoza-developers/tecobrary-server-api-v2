/*
 * @(#) GithubApiResponseVo.java
 *
 * v 1.0.0
 *
 * 2019.11.29
 *
 * Copyright (c) 2019 woowacourse, thedevluffy
 * All rights reserved
 */

package com.woowacourse.tecobrary.user.ui.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class GithubApiResponseVo {

    private UserJwtInfoVo user;
    private String token;

    public GithubApiResponseVo(UserJwtInfoVo user, String token) {
        this.user = user;
        this.token = token;
    }
}
