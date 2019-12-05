package com.woowacourse.tecobrary.librarybook.ui.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LibraryBookEnrollDto implements LibraryBookDto {

    private Long id;
    private String image;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String description;
    private LocalDateTime enrolledDate;

    @Builder
    private LibraryBookEnrollDto(final Long id, final String image, final String title, final String author, final String publisher, final String isbn, final String description, final LocalDateTime enrolledDate) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.description = description;
        this.enrolledDate = enrolledDate;
    }
}
