package com.woowacourse.tecobrary.common.ui.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class NaverApiDto {

    private String lastBuildDate;
    private Long total;
    private Long start;
    private Long display;
    private List<NaverApiItemDto> items;

    public NaverApiDto(final String lastBuildDate,
                       final Long total,
                       final Long start,
                       final Long display,
                       final List<NaverApiItemDto> items) {
        this.lastBuildDate = lastBuildDate;
        this.total = total;
        this.start = start;
        this.display = display;
        this.items = items;
    }
}
