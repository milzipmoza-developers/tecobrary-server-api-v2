package com.woowacourse.tecobrary.admin.web.wishbook.service;

import com.woowacourse.tecobrary.admin.web.wishbook.dto.WishBookCancelRequest;
import com.woowacourse.tecobrary.admin.web.wishbook.dto.WishBookEnrollRequest;
import com.woowacourse.tecobrary.admin.web.wishbook.dto.WishBookSearchRequest;
import com.woowacourse.tecobrary.admin.web.wishbook.repository.WishBookAdminRepository;
import com.woowacourse.tecobrary.admin.web.wishbook.repository.WishBookSearchClause;
import com.woowacourse.tecobrary.domain.wishbook.entity.WishBook;
import com.woowacourse.tecobrary.domain.wishbook.repository.WishBookRepository;
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
    private final WishBookRepository wishBookRepository;

    public Page<WishBook> getWishBooks(Pageable pageable, WishBookSearchRequest request) {
        WishBookSearchClause searchClause = WishBookSearchClause.builder()
                .requestUser(request.getRequestUser())
                .status(request.getStatus())
                .build();

        return wishBookAdminRepository.findAllByCondition(pageable, searchClause);
    }

    public WishBook enrollWishBook(WishBookEnrollRequest request) {
        WishBook wishBook = getWishBook(request.getId());
        checkHandled(wishBook);
        wishBook.enrollWishBook();

        return wishBook;
    }

    public WishBook cancelWishBook(WishBookCancelRequest request) {
        WishBook wishBook = getWishBook(request.getId());
        checkHandled(wishBook);
        wishBook.cancelWishBook(request.getReason());

        return wishBook;
    }

    private WishBook getWishBook(Long id) {
        return wishBookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 희망도서 입니다."));
    }

    private void checkHandled(WishBook wishBook) {
        if (wishBook.isHandled()) {
            throw new IllegalArgumentException("이미 처리된 희망도서 입니다.");
        }
    }
}
