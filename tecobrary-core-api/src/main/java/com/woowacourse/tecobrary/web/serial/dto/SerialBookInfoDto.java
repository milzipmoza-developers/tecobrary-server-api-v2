package com.woowacourse.tecobrary.web.serial.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SerialBookInfoDto {

    private final Long id;
    private final String image;
    private final String title;
    private final String author;
    private final String publisher;
    private final String isbn;

    @Builder
    public SerialBookInfoDto(Long id,
                             String image,
                             String title,
                             String author,
                             String publisher,
                             String isbn) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
    }
}
