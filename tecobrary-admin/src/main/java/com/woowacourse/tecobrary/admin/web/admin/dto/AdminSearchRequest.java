package com.woowacourse.tecobrary.admin.web.admin.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class AdminSearchRequest {

    private String email;
    private String name;

    public AdminSearchRequest(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
