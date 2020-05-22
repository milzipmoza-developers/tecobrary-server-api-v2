package com.woowacourse.tecobrary.admin.web.admin.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class AdminSearchResponse {

    private Long id;
    private String email;
    private String name;
    private String role;

    @Builder
    public AdminSearchResponse(Long id, String email, String name, String role) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
    }
}
