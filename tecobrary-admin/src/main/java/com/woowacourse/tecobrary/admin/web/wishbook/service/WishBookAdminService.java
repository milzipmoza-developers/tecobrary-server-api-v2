package com.woowacourse.tecobrary.admin.web.wishbook.service;

import com.woowacourse.tecobrary.admin.web.wishbook.dto.WishBookSearchRequest;
import com.woowacourse.tecobrary.admin.web.wishbook.repository.WishBookAdminRepository;
import com.woowacourse.tecobrary.admin.web.wishbook.repository.WishBookSearchClause;
import com.woowacourse.tecobrary.domain.wishbook.entity.WishBook;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class WishBookAdminService {

    private final WishBookAdminRepository wishBookAdminRepository;

    public Page<WishBook> getWishBooks(Pageable pageable, WishBookSearchRequest request) {
        WishBookSearchClause searchClause = WishBookSearchClause.builder()
                .requestUser(request.getRequestUser())
                .status(request.getStatus())
                .build();

        return wishBookAdminRepository.findAllByCondition(pageable, searchClause);
    }
}
