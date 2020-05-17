package com.woowacourse.tecobrary.admin.web.wishbook.dto;

import com.woowacourse.tecobrary.domain.wishbook.entity.WishBookStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class WishBookHandleResponse {

    private Long id;
    private WishBookStatus status;
    private String message;

    @Builder
    public WishBookHandleResponse(Long id, WishBookStatus status, String message) {
        this.id = id;
        this.status = status;
        this.message = message;
    }
}
