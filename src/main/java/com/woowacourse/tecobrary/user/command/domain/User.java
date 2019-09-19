package com.woowacourse.tecobrary.user.command.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
