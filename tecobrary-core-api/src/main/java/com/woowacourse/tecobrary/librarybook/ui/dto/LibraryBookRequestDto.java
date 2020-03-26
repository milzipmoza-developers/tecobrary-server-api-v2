package com.woowacourse.tecobrary.librarybook.ui.dto;

import com.woowacourse.tecobrary.web.tecorvis.dto.SlackBotModeDto;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public class LibraryBookRequestDto implements LibraryBookDto {

    private static final boolean SLACK_BOT_DEFAULT_MODE = true;

    private String image;
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String description;
    private SlackBotModeDto slackBotMode = new SlackBotModeDto(SLACK_BOT_DEFAULT_MODE);

    @Builder
    private LibraryBookRequestDto(final String image, final String title, final String author, final String publisher, final String isbn, final String description) {
        this.image = image;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.description = description;
    }
}
