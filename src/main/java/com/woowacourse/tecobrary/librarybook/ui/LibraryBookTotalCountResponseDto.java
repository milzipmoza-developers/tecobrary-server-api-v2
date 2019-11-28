package com.woowacourse.tecobrary.librarybook.ui;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class LibraryBookTotalCountResponseDto {

    private final long total;

    public LibraryBookTotalCountResponseDto(final long total) {
        this.total = total;
    }
}
