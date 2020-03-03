package com.woowacourse.tecobrary.librarybook.util;

import com.woowacourse.tecobrary.common.entity.DefaultBook;
import com.woowacourse.tecobrary.tecorvis.dto.SlackBotBookInfoDto;

public class BookConverter {

    public static SlackBotBookInfoDto convert(final DefaultBook book) {
        return SlackBotBookInfoDto.builder()
                .title(book.getTitle())
                .author(book.getAuthor())
                .publisher(book.getPublisher())
                .isbn(book.getIsbn())
                .createdAt(book.getCreatedAt())
                .build();
    }
}
