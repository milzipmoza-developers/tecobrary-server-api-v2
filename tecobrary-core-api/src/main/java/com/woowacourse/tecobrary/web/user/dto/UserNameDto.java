package com.woowacourse.tecobrary.web.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class UserNameDto {

    private Long id;
    private String newName;

    public UserNameDto(final Long id, final String newName) {
        this.id = id;
        this.newName = newName;
    }
}
