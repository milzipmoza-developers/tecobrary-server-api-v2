package com.woowacourse.tecobrary.wishbook.command.util;

import com.woowacourse.tecobrary.wishbook.command.domain.WishBook;
import com.woowacourse.tecobrary.wishbook.ui.dto.WishBookInfoDto;

public class WishBookInfoDtoMapper {

    public static WishBookInfoDto toDto(final WishBook wishBook) {
        return WishBookInfoDto.builder()
                .image(wishBook.getImage())
                .title(wishBook.getTitle())
                .author(wishBook.getAuthor())
                .publisher(wishBook.getPublisher())
                .isbn(wishBook.getIsbn())
                .description(wishBook.getDescription())
                .userId(wishBook.getUserId())
                .build();
    }
}
