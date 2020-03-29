package com.woowacourse.tecobrary.user;

import com.woowacourse.tecobrary.common.entity.ModifiableEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
public class User extends ModifiableEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(unique = true, nullable = false, length = 25)
    private String githubId;

    @Column(nullable = false, length = 25)
    private String name;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String avatarUrl;

    @Column(nullable = false)
    private String authorization;

    @Builder
    public User(String githubId,
                String name,
                String email,
                String avatarUrl,
                String authorization) {
        this.githubId = githubId;
        this.name = name;
        this.email = email;
        this.avatarUrl = avatarUrl;
        this.authorization = authorization;
    }

    public String updateName(String name) {
        if (name.length() > 25) {
            throw new IllegalArgumentException("이름은 25자를 초과할 수 없습니다.");
        }
        return this.name = name;
    }

    public String updateAuthorization(String authorization) {
        return this.authorization = authorization;
    }
}
