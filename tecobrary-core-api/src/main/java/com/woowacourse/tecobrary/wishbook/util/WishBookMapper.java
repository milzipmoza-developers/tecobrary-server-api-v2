package com.woowacourse.tecobrary.wishbook.util;

import com.woowacourse.tecobrary.wishbook.entity.WishBook;
import com.woowacourse.tecobrary.wishbook.ui.dto.WishBookDto;

public class WishBookMapper {

    public static WishBookDto toDto(final WishBook wishBook) {
        return WishBookDto.builder()
                .title(wishBook.getTitle())
                .author(wishBook.getAuthor())
                .publisher(wishBook.getPublisher())
                .isbn(wishBook.getIsbn())
                .createdAt(wishBook.getCreatedAt())
                .build();
    }
}
