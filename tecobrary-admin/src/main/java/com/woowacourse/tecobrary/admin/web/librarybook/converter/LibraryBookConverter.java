package com.woowacourse.tecobrary.admin.web.librarybook.converter;

import com.woowacourse.tecobrary.admin.web.librarybook.dto.LibraryBookDescResponse;
import com.woowacourse.tecobrary.admin.web.librarybook.dto.LibraryBookInfoResponse;
import com.woowacourse.tecobrary.domain.librarybook.entity.LibraryBook;
import org.springframework.stereotype.Component;

@Component
public class LibraryBookConverter {

    public LibraryBookInfoResponse convertInfo(LibraryBook libraryBook) {
        return LibraryBookInfoResponse.builder()
                .id(libraryBook.getId())
                .author(libraryBook.getAuthor())
                .publisher(libraryBook.getPublisher())
                .isbn(libraryBook.getIsbn())
                .bookCounts(libraryBook.getSerialList().size())
                .build();
    }

    public LibraryBookDescResponse convertDesc(LibraryBook libraryBook) {
        return LibraryBookDescResponse.builder()
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
