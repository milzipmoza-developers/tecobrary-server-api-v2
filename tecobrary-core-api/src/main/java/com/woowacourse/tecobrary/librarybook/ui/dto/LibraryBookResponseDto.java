package com.woowacourse.tecobrary.librarybook.ui.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LibraryBookResponseDto implements LibraryBookDto {

    private Long id;
    private String image;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String description;

    @Builder
    private LibraryBookResponseDto(final Long id, final String image, final String title, final String author, final String publisher, final String isbn, final String description) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.description = description;
    }
}
