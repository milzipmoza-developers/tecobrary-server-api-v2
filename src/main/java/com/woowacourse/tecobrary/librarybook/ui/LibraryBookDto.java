package com.woowacourse.tecobrary.librarybook.ui;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class LibraryBookDto {
    private String image;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String description;

    public LibraryBookDto(String image, String title, String author, String publisher, String isbn, String description) {
        this.image = image;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.description = description;
    }
}
