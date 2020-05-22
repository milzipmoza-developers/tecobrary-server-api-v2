package com.woowacourse.tecobrary.admin.web.admin.dto;


import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class AdminCreateResponse {

    private Long id;
    private String email;

    @Builder
    public AdminCreateResponse(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
