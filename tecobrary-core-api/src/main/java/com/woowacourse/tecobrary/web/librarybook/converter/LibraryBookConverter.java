package com.woowacourse.tecobrary.web.librarybook.converter;

import com.woowacourse.tecobrary.librarybook.entity.LibraryBook;
import com.woowacourse.tecobrary.web.librarybook.dto.LibraryBookDetailDto;
import org.springframework.stereotype.Component;

@Component
public class LibraryBookConverter {

    public LibraryBookDetailDto convertDetailDto(LibraryBook libraryBook) {
        return LibraryBookDetailDto.builder()
                .id(libraryBook.getId())
                .title(libraryBook.getTitle())
                .image(libraryBook.getImage())
                .author(libraryBook.getAuthor())
                .publisher(libraryBook.getPublisher())
                .isbn(libraryBook.getIsbn())
                .description(libraryBook.getDescription())
                .build();
    }
}
