package com.woowacourse.tecobrary.admin.web.wishbook.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.woowacourse.tecobrary.domain.wishbook.entity.WishBookStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    @Builder
    public WishBookInfoResponse(Long id, String title, String author, String publisher, String isbn, WishBookStatus status, String requestUser, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.status = status;
        this.requestUser = requestUser;
        this.createdAt = createdAt;
    }
}
