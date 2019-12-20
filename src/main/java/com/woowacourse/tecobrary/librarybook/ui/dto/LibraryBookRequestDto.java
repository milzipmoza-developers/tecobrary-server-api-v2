package com.woowacourse.tecobrary.librarybook.ui.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class LibraryBookRequestDto implements LibraryBookDto {

    private String image;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String description;

    @Builder
    private LibraryBookRequestDto(final String image, final String title, final String author, final String publisher, final String isbn, final String description) {
        this.image = image;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.description = description;
    }
}
