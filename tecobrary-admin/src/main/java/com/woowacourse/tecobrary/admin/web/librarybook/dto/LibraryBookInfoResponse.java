package com.woowacourse.tecobrary.admin.web.librarybook.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class LibraryBookInfoResponse {

    private Long id;
    private String image;
    private String title;
    private String author;
    private String publisher;
    private Integer bookCounts;

    @Builder
    public LibraryBookInfoResponse(Long id,
                                   String image,
                                   String title,
                                   String author,
                                   String publisher,
                                   Integer bookCounts) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.bookCounts = bookCounts;
    }
}
