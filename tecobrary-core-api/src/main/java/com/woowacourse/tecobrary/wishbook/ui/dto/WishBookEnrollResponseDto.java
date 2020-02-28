package com.woowacourse.tecobrary.wishbook.ui.dto;

import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookDto;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;


@Getter
@ToString
public class WishBookEnrollResponseDto {

    private LibraryBookDto libraryBook;
    private LocalDateTime enrolledDate;

    public WishBookEnrollResponseDto(final LibraryBookDto libraryBook, final LocalDateTime enrolledDate) {
        this.libraryBook = libraryBook;
        this.enrolledDate = enrolledDate;
    }
}
