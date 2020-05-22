package com.woowacourse.tecobrary.admin.web.admin.dto;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class AdminCreateRequest {

    private String email;

    public AdminCreateRequest(String email) {
        this.email = email;
    }
}
