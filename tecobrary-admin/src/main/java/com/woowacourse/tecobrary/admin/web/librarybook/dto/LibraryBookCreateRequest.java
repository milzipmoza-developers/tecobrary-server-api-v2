package com.woowacourse.tecobrary.admin.web.librarybook.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class LibraryBookCreateRequest {

    private String image;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String description;

    public LibraryBookCreateRequest(String image, String title, String author, String publisher, String isbn, String description) {
        this.image = image;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.description = description;
    }
}
