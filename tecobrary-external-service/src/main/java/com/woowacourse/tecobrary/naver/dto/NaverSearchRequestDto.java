package com.woowacourse.tecobrary.naver.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NaverSearchRequestDto {

    private String query;
    private Long display;
    private Long start;

    public NaverSearchRequestDto(final String query, final Long display, final Long start) {
        this.query = query;
        this.display = display;
        this.start = start;
    }
}
