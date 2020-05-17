package com.woowacourse.tecobrary.admin.web.librarybook.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class LibraryBookInfoResponse {

    private Long id;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private Integer bookCounts;

    @Builder
    public LibraryBookInfoResponse(Long id,
                                   String title,
                                   String author,
                                   String publisher,
                                   String isbn,
                                   Integer bookCounts) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.bookCounts = bookCounts;
    }
}
