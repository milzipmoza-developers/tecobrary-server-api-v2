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

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "isbn", unique = true)
    private String isbn;

    @Column(name = "desc")
    private String desc;

    @Builder
    public BookInfo(String title, String author, String publisher, String isbn, String desc) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.desc = desc;
    }
}
