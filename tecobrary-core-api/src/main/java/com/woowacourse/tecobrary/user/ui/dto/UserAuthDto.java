package com.woowacourse.tecobrary.user.ui.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class UserAuthDto {

    private Long id;
    private String authorization;

    public UserAuthDto(final Long id, final String authorization) {
        this.id = id;
        this.authorization = authorization;
    }
}
