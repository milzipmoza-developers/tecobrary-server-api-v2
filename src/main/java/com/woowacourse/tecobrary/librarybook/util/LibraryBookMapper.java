package com.woowacourse.tecobrary.librarybook.util;

import com.woowacourse.tecobrary.common.model.BookCoverUrl;
import com.woowacourse.tecobrary.common.model.BookInfo;
import com.woowacourse.tecobrary.librarybook.domain.LibraryBook;
import com.woowacourse.tecobrary.librarybook.ui.LibraryBookRequestDto;
import com.woowacourse.tecobrary.librarybook.ui.LibraryBookResponseDto;

public class LibraryBookMapper {

    public static LibraryBook toEntity(final LibraryBookRequestDto libraryBookRequestDto) {
        return new LibraryBook(
                new BookCoverUrl(libraryBookRequestDto.getImage()),
                new BookInfo(libraryBookRequestDto.getTitle(),
                        libraryBookRequestDto.getAuthor(),
                        libraryBookRequestDto.getPublisher(),
                        libraryBookRequestDto.getIsbn(),
                        libraryBookRequestDto.getDescription()));
    }

    public static LibraryBookResponseDto toDto(final LibraryBook libraryBook) {
        return new LibraryBookResponseDto(libraryBook.getId(),
                libraryBook.getImage(),
                libraryBook.getTitle(),
                libraryBook.getAuthor(),
                libraryBook.getPublisher(),
                libraryBook.getIsbn(),
                libraryBook.getDescription());
    }
}
