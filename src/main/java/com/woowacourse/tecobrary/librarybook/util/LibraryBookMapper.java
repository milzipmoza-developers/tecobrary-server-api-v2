package com.woowacourse.tecobrary.librarybook.util;

import com.woowacourse.tecobrary.common.model.BookCoverUrl;
import com.woowacourse.tecobrary.common.model.BookInfo;
import com.woowacourse.tecobrary.librarybook.domain.LibraryBook;
import com.woowacourse.tecobrary.librarybook.ui.LibraryBookDto;

public class LibraryBookMapper {

    public static LibraryBook map(final LibraryBookDto libraryBookDto) {
        return new LibraryBook(
                new BookCoverUrl(libraryBookDto.getImage()),
                new BookInfo(libraryBookDto.getTitle(),
                        libraryBookDto.getAuthor(),
                        libraryBookDto.getPublisher(),
                        libraryBookDto.getIsbn(),
                        libraryBookDto.getDescription()));
    }
}
