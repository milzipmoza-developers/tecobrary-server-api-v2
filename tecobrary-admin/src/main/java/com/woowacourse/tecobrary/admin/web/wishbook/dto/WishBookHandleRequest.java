package com.woowacourse.tecobrary.admin.web.wishbook.dto;

import com.woowacourse.tecobrary.domain.wishbook.entity.WishBookStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class WishBookHandleRequest {

    private Long id;
    private WishBookStatus wishBookStatus;
    private String reason;

    public WishBookHandleRequest(Long id, WishBookStatus wishBookStatus, String reason) {
        this.id = id;
        this.wishBookStatus = wishBookStatus;
        this.reason = reason;
    }
}
