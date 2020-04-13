package com.woowacourse.tecobrary.admin.web.wishbook.dto;

import com.woowacourse.tecobrary.domain.wishbook.entity.WishBookStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class WishBookSearchRequest {

    private String requestUser;
    private WishBookStatus status;

    public WishBookSearchRequest(String requestUser, WishBookStatus status) {
        this.requestUser = requestUser;
        this.status = status;
    }
}
