package com.woowacourse.tecobrary.user.ui.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class GithubEmailVo {
    private String email;
    private boolean primary;
    private boolean verified;
    private String visibility;

    public GithubEmailVo(String email, boolean primary, boolean verified, String visibility) {
        this.email = email;
        this.primary = primary;
        this.verified = verified;
        this.visibility = visibility;
    }
}
