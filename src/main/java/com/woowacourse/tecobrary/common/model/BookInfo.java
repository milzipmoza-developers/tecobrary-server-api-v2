package com.woowacourse.tecobrary.common.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class BookInfo {

    @Column(name = "title",
            nullable = false)
    private String title;

    @Column(name = "author",
            nullable = false)
    private String author;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "isbn",
            unique = true,
            nullable = false)
    private String isbn;

    @Column(name = "description",
            length = 500,
            columnDefinition = "varchar(500) default '내용 없음'")
    private String description;

    @Builder
    public BookInfo(String title, String author, String publisher, String isbn, String description) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.description = description;
    }
}
