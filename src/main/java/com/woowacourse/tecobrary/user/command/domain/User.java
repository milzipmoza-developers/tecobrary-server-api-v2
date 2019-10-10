package com.woowacourse.tecobrary.user.command.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@EqualsAndHashCode(of = "userNo")
@Table(name = "User")
@Access(AccessType.FIELD)
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long userNo;

    @Embedded
    private UserGithubInfo userGithubInfo;

    @Embedded
    private UserAuthorization userAuthorization;

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
}
