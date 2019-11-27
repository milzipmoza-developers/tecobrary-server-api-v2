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
