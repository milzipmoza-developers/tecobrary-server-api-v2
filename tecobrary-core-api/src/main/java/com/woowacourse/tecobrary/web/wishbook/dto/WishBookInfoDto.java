package com.woowacourse.tecobrary.web.wishbook.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

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
    public WishBookInfoDto(String image,
                           String title,
                           String author,
                           String publisher,
                           String isbn,
                           String description,
                           Long userId) {
        this.image = image;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.description = description;
        this.userId = userId;
    }
}
