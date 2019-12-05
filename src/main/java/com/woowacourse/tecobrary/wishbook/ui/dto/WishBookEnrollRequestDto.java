package com.woowacourse.tecobrary.wishbook.ui.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class WishBookEnrollRequestDto {

    private Long id;

    public WishBookEnrollRequestDto(final Long id) {
        this.id = id;
    }
}
