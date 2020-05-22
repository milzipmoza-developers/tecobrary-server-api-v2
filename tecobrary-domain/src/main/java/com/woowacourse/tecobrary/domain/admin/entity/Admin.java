package com.woowacourse.tecobrary.domain.admin.entity;

import com.woowacourse.tecobrary.domain.common.entity.DeletableEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
public class Admin extends DeletableEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false, length = 100)
    private String picture;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AdminRole role;

    @Builder
    public Admin(String email, String name, String picture) {
        this.email = email;
        this.name = name;
        this.picture = picture;
        this.role = AdminRole.ROLE_NO;
    }

    public Admin updateUserInfo(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }

    public Admin updateRole(AdminRole role) {
        this.role = role;
        return this;
    }
}
