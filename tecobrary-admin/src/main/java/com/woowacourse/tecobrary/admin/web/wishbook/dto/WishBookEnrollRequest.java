package com.woowacourse.tecobrary.admin.web.wishbook.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class WishBookEnrollRequest {

    private Long id;

    public WishBookEnrollRequest(Long id) {
        this.id = id;
    }
}
