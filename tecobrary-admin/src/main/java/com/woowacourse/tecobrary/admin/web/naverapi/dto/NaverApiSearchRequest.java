package com.woowacourse.tecobrary.admin.web.naverapi.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class NaverApiSearchRequest {

    private String keyword;
    private Long page;
    private Long number;

    public NaverApiSearchRequest(String keyword, Long page, Long number) {
        this.keyword = keyword;
        this.page = page;
        this.number = number;
    }
}
