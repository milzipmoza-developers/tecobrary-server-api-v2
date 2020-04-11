package com.woowacourse.tecobrary.admin.web.librarybook.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class LibraryBookSearchRequest {

    private String title;
    private String author;
    private String publisher;
    private String isbn;

    public LibraryBookSearchRequest(String title, String author, String publisher, String isbn) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
    }
}
