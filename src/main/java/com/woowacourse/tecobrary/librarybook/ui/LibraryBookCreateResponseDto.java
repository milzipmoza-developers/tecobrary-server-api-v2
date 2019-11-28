package com.woowacourse.tecobrary.librarybook.ui;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LibraryBookCreateResponseDto {

    private final Long id;
    private final String message;

    public LibraryBookCreateResponseDto(final Long id, final String message) {
        this.id = id;
        this.message = message;
    }
}
