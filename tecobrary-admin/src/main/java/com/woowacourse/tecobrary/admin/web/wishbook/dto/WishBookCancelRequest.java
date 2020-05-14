package com.woowacourse.tecobrary.admin.web.wishbook.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class WishBookCancelRequest {

    private Long id;
    private String reason;

    public WishBookCancelRequest(Long id, String reason) {
        this.id = id;
        this.reason = reason;
    }
}
