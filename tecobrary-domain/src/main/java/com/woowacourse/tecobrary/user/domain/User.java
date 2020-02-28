/*
 * @(#) User.java
 *
 * v 1.0.0
 *
 * 2019.11.29
 *
 * Copyright (c) 2019 woowacourse, thedevluffy, gch01410, LeeYounghyeon
 * All rights reserved
 */

package com.woowacourse.tecobrary.user.domain;

import com.woowacourse.tecobrary.common.entity.ModifiableEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "Users")
@Access(AccessType.FIELD)
public class User extends ModifiableEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Embedded
    private UserGithubInfo userGithubInfo;

    @Embedded
    private UserAuthorization userAuthorization;

    public User(final UserGithubInfo userGithubInfo, final UserAuthorization userAuthorization) {
        this.userGithubInfo = userGithubInfo;
        this.userAuthorization = userAuthorization;
    }

    public String updateUserName(final String newName) {
        return userGithubInfo.updateName(newName);
    }

    public String updateAuthorization(final String newAuthorization) {
        return userAuthorization.updateAuthorization(newAuthorization);
    }

    public String getGithubId() {
        return userGithubInfo.getGithubId();
    }

    public String getUserEmail() {
        return userGithubInfo.getEmailContent();
    }

    public String getUserName() {
        return userGithubInfo.getNameContent();
    }

    public String getUserAvatarUrl() {
        return userGithubInfo.getAvatarUrlContent();
    }

    public String getAuthorization() {
        return userAuthorization.getAuthorizationContent();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userGithubInfo=" + userGithubInfo +
                ", userAuthorization=" + userAuthorization +
                '}';
    }
}
