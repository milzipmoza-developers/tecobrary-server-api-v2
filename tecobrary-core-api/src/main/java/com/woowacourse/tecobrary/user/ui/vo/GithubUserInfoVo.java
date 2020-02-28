/*
 * @(#) GithubUserInfoVo.java
 *
 * v 1.0.0
 *
 * 2019.11.29
 *
 * Copyright (c) 2019 woowacourse, thedevluffy
 * All rights reserved
 */

package com.woowacourse.tecobrary.user.ui.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(of = "id")
public class GithubUserInfoVo {

    private String id;
    private String avatar_url;
    private String name;

    public GithubUserInfoVo(String id, String avatar_url, String name) {
        this.id = id;
        this.avatar_url = avatar_url;
        this.name = name;
    }
}
