package com.woowacourse.tecobrary.web.tecorvis.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SlackBotBookInfoDto {

    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private LocalDateTime createdAt;

    @Builder
    public SlackBotBookInfoDto(String title, String author, String publisher, String isbn, LocalDateTime createdAt) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.createdAt = createdAt;
    }
}
