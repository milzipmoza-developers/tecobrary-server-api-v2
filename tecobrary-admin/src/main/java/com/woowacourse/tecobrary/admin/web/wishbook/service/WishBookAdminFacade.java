package com.woowacourse.tecobrary.admin.web.wishbook.service;

import com.woowacourse.tecobrary.admin.web.wishbook.converter.WishBookConverter;
import com.woowacourse.tecobrary.admin.web.wishbook.dto.WishBookInfoResponse;
import com.woowacourse.tecobrary.admin.web.wishbook.dto.WishBookSearchRequest;
import com.woowacourse.tecobrary.domain.wishbook.entity.WishBook;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class WishBookAdminFacade {

    private final WishBookAdminService wishBookAdminService;
    private final WishBookConverter wishBookConverter;

    public Page<WishBookInfoResponse> getWishBooks(Pageable pageable, WishBookSearchRequest request) {
        Page<WishBook> wishbooks = wishBookAdminService.getWishBooks(pageable, request);

        List<WishBookInfoResponse> dtos = wishbooks.stream()
                .map(wishBookConverter::convertInfo)
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, wishbooks.getTotalElements());
    }
}
