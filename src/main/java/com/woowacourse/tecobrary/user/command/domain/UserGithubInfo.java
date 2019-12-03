/*
 * @(#) UserGithubInfo.java
 *
 * v 1.0.0
 *
 * 2019.11.29
 *
 * Copyright (c) 2019 woowacourse, thedevluffy
 * All rights reserved
 */

package com.woowacourse.tecobrary.user.command.domain;

import com.woowacourse.tecobrary.common.model.Email;
import com.woowacourse.tecobrary.common.model.HttpsUrl;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class UserGithubInfo {

    @Column(name = "githubId",
            unique = true,
            nullable = false,
            length = 100)
    private String githubId;

    @Embedded
    private UserName name;

    @Embedded
    private Email email;

    @Embedded
    @AttributeOverride(
            name = "url",
            column = @Column(name = "avatarUrl", nullable = false))
    private HttpsUrl httpsUrl;

    public UserGithubInfo(String githubId, UserName name, Email email, HttpsUrl httpsUrl) {
        this.githubId = githubId;
        this.name = name;
        this.email = email;
        this.httpsUrl = httpsUrl;
    }

    String updateName(String newName) {
        this.name = new UserName(newName);
        return name.getName();
    }

    String getEmailContent() {
        return email.getEmail();
    }

    String getNameContent() {
        return name.getName();
    }

    String getAvatarUrlContent() {
        return httpsUrl.getUrl();
    }
}
