package com.woowacourse.tecobrary.librarybook.util;

import com.woowacourse.tecobrary.common.model.BookCoverUrl;
import com.woowacourse.tecobrary.common.model.BookInfo;
import com.woowacourse.tecobrary.librarybook.domain.LibraryBook;
import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookDto;
import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookEnrollDto;
import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookResponseDto;
import com.woowacourse.tecobrary.wishbook.command.domain.WishBook;

public class LibraryBookMapper {

    public static LibraryBook toEntity(final LibraryBookDto libraryBookDto) {
        return new LibraryBook(
                new BookCoverUrl(libraryBookDto.getImage()),
                new BookInfo(libraryBookDto.getTitle(),
                        libraryBookDto.getAuthor(),
                        libraryBookDto.getPublisher(),
                        libraryBookDto.getIsbn(),
                        libraryBookDto.getDescription()));
    }

    public static LibraryBookResponseDto toResponseDto(final LibraryBook libraryBook) {
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

    public static LibraryBookEnrollDto toEnrollDto(final LibraryBook enrolledBook) {
        return  LibraryBookEnrollDto.builder()
                .id(enrolledBook.getId())
                .image(enrolledBook.getImage())
                .title(enrolledBook.getTitle())
                .author(enrolledBook.getAuthor())
                .publisher(enrolledBook.getPublisher())
                .isbn(enrolledBook.getIsbn())
                .description(enrolledBook.getDescription())
                .enrolledDate(enrolledBook.getCreatedAt())
                .build();
    }

    public static LibraryBookEnrollDto toEnrollDto(final WishBook wishBook) {
        return LibraryBookEnrollDto.builder()
                .id(wishBook.getId())
                .image(wishBook.getImage())
                .title(wishBook.getTitle())
                .author(wishBook.getAuthor())
                .publisher(wishBook.getPublisher())
                .isbn(wishBook.getIsbn())
                .description(wishBook.getDescription())
                .build();
    }
}
