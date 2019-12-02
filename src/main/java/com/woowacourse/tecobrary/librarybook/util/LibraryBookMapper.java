package com.woowacourse.tecobrary.librarybook.util;

import com.woowacourse.tecobrary.common.model.BookCoverUrl;
import com.woowacourse.tecobrary.common.model.BookInfo;
import com.woowacourse.tecobrary.librarybook.domain.LibraryBook;
import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookRequestDto;
import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookResponseDto;

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
        return LibraryBookResponseDto.builder()
                .id(libraryBook.getId())
                .image(libraryBook.getImage())
                .title(libraryBook.getTitle())
                .author(libraryBook.getAuthor())
                .publisher(libraryBook.getPublisher())
                .isbn(libraryBook.getIsbn())
                .description(libraryBook.getDescription())
                .build();
    }
}
