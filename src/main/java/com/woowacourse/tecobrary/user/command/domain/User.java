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

package com.woowacourse.tecobrary.user.command.domain;

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
@EqualsAndHashCode(of = "id")
@Table(name = "Users")
@Access(AccessType.FIELD)
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Embedded
    private UserGithubInfo userGithubInfo;

    @Embedded
    private UserAuthorization userAuthorization;

    @CreationTimestamp
    @Column(name = "createdAt",
            updatable = false,
            nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt",
            nullable = false)
    private LocalDateTime updatedAt;

    public User(UserGithubInfo userGithubInfo, UserAuthorization userAuthorization) {
        this.userGithubInfo = userGithubInfo;
        this.userAuthorization = userAuthorization;
    }

    public String updateUserName(String newName) {
        return userGithubInfo.updateName(newName);
    }

    public String updateAuthorization(String newAuthorization) {
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
