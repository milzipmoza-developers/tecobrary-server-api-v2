package com.woowacourse.tecobrary.external.dto;

import lombok.Getter;

@Getter
public class NaverApiSearchRequest {

    private final String keyword;
    private final Long page;
    private final Long number;

    public NaverApiSearchRequest(String keyword, Long page, Long number) {
        this.keyword = keyword;
        this.page = page;
        this.number = number;
    }
}
