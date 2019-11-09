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

    @CreationTimestamp
    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt", nullable = false)
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
                "userNo=" + userNo +
                ", userGithubInfo=" + userGithubInfo +
                ", userAuthorization=" + userAuthorization +
                '}';
    }
}
