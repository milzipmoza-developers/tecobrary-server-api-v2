package com.woowacourse.tecobrary.user.ui.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class UserResponseVo {

    private String email;
    private String name;
    private String avatarUrl;
    private String authorization;

    public UserResponseVo(String email, String name, String avatarUrl, String authorization) {
        this.email = email;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.authorization = authorization;
    }
}
