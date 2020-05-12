package com.woowacourse.tecobrary.web.librarybook.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LibraryBookDetailDto {

    private final Long id;
    private final String image;
    private final String title;
    private final String author;
    private final String publisher;
    private final String isbn;
    private final String description;

    @Builder
    public LibraryBookDetailDto(Long id,
                                String image,
                                String title,
                                String author,
                                String publisher,
                                String isbn,
                                String description) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.description = description;
    }
}
