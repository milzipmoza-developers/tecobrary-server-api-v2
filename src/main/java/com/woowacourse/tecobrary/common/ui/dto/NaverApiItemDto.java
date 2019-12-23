package com.woowacourse.tecobrary.common.ui.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class NaverApiItemDto {

    private String title;
    private String link;
    private String image;
    private String author;
    private String price;
    private String discount;
    private String publisher;
    private String pubDate;
    private String isbn;
    private String description;

    public NaverApiItemDto(final String title,
                           final String link,
                           final String image,
                           final String author,
                           final String price,
                           final String discount,
                           final String publisher,
                           final String pubDate,
                           final String isbn,
                           final String description) {
        this.title = title;
        this.link = link;
        this.image = image;
        this.author = author;
        this.price = price;
        this.discount = discount;
        this.publisher = publisher;
        this.pubDate = pubDate;
        this.isbn = isbn;
        this.description = description;
    }
}
