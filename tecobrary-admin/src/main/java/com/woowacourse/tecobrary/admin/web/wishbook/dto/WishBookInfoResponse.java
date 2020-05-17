package com.woowacourse.tecobrary.admin.web.wishbook.dto;

import com.woowacourse.tecobrary.domain.wishbook.entity.WishBookStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class WishBookInfoResponse {

    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private WishBookStatus status;
    private String requestUser;

    @Builder
    public WishBookInfoResponse(Long id, String title, String author, String publisher, String isbn, WishBookStatus status, String requestUser) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.status = status;
        this.requestUser = requestUser;
    }
}
