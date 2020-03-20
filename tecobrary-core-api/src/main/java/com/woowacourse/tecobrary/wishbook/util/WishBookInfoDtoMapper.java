package com.woowacourse.tecobrary.wishbook.util;

import com.woowacourse.tecobrary.common.domain.BookCoverUrl;
import com.woowacourse.tecobrary.common.domain.BookInfo;
import com.woowacourse.tecobrary.wishbook.entity.WishBook;
import com.woowacourse.tecobrary.wishbook.entity.WishBookRequestUser;
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

    public static WishBook toEntity(final WishBookInfoDto wishBookInfoDto) {
        return new WishBook(
                new BookCoverUrl(wishBookInfoDto.getImage()),
                new BookInfo(
                        wishBookInfoDto.getTitle(),
                        wishBookInfoDto.getAuthor(),
                        wishBookInfoDto.getPublisher(),
                        wishBookInfoDto.getIsbn(),
                        wishBookInfoDto.getDescription()),
                new WishBookRequestUser(wishBookInfoDto.getUserId()));
    }
}
