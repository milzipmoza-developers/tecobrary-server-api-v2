package com.woowacourse.tecobrary.web.wishbook.converter;

import com.woowacourse.tecobrary.web.wishbook.dto.WishBookInfoDto;
import com.woowacourse.tecobrary.wishbook.entity.WishBook;
import org.springframework.stereotype.Component;

@Component
public class WishBookConverter {

    public WishBookInfoDto convertInfoDto(WishBook wishBook) {
        return WishBookInfoDto.builder()
                .title(wishBook.getTitle())
                .image(wishBook.getImage())
                .author(wishBook.getAuthor())
                .isbn(wishBook.getIsbn())
                .publisher(wishBook.getPublisher())
                .description(wishBook.getDescription())
                .userId(wishBook.getUser().getId())
                .build();
    }
}
