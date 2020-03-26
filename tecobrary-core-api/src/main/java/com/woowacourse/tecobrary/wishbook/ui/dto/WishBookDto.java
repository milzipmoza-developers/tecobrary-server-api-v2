package com.woowacourse.tecobrary.wishbook.ui.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class WishBookDto {

    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private LocalDateTime createdAt;

    @Builder
    public WishBookDto(final String title, final String author, final String publisher, final String isbn, final LocalDateTime createdAt) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.createdAt = createdAt;
    }
}
