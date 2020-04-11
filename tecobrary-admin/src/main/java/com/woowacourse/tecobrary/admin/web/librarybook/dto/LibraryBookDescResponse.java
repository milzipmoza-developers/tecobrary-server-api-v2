package com.woowacourse.tecobrary.admin.web.librarybook.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class LibraryBookDescResponse {

    private Long id;
    private String image;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String description;

    @Builder
    public LibraryBookDescResponse(Long id, String image, String title, String author, String publisher, String isbn, String description) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.description = description;
    }
}
