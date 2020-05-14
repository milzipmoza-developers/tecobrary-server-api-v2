package com.woowacourse.tecobrary.admin.web;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@Getter
@ToString
public class AdminUserDto {

    private String name;
    private String email;
    private String picture;
    private List<String> roles;

    @Builder
    public AdminUserDto(String name, String email, String picture) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.roles = Collections.singletonList("ADMIN");
    }
}
