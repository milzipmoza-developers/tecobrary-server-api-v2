package com.woowacourse.tecobrary.wishbook.ui.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class WishBookInfoDto {

    private String image;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String description;
    private Long userId;

    @Builder
    public WishBookInfoDto(final String image, final String title, final String author, final String publisher, final String isbn, final String description, final Long userId) {
        this.image = image;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.description = description;
        this.userId = userId;
    }
}
