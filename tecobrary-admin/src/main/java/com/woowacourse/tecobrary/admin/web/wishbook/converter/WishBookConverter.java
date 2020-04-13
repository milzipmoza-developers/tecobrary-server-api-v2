package com.woowacourse.tecobrary.admin.web.wishbook.converter;

import com.woowacourse.tecobrary.admin.web.wishbook.dto.WishBookInfoResponse;
import com.woowacourse.tecobrary.domain.wishbook.entity.WishBook;
import org.springframework.stereotype.Component;

@Component
public class WishBookConverter {
    public WishBookInfoResponse convertInfo(WishBook wishBook) {
        return WishBookInfoResponse.builder()
                .id(wishBook.getId())
                .title(wishBook.getTitle())
                .author(wishBook.getAuthor())
                .publisher(wishBook.getPublisher())
                .isbn(wishBook.getIsbn())
                .status(wishBook.getStatus())
                .requestUser(wishBook.getUser().getName())
                .build();
    }
}
