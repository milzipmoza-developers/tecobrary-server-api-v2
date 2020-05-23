package com.woowacourse.tecobrary.admin.web.wishbook.service;

import com.woowacourse.tecobrary.admin.web.wishbook.dto.WishBookHandleRequest;
import com.woowacourse.tecobrary.admin.web.wishbook.dto.WishBookSearchRequest;
import com.woowacourse.tecobrary.admin.web.wishbook.exception.WishBookAlreadyHandledException;
import com.woowacourse.tecobrary.admin.web.wishbook.exception.WishBookNotFoundException;
import com.woowacourse.tecobrary.admin.web.wishbook.exception.WishBookStatusChangeFailedException;
import com.woowacourse.tecobrary.admin.web.wishbook.repository.WishBookAdminRepository;
import com.woowacourse.tecobrary.admin.web.wishbook.repository.WishBookSearchClause;
import com.woowacourse.tecobrary.domain.wishbook.entity.WishBook;
import com.woowacourse.tecobrary.domain.wishbook.entity.WishBookStatus;
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

    public WishBook handleWishBook(WishBookHandleRequest request) {
        if (WishBookStatus.ENROLLED == request.getWishBookStatus()) {
            return enrollWishBook(request);
        }

        if (WishBookStatus.CANCELED == request.getWishBookStatus()) {
            return cancelWishBook(request);
        }

        throw new WishBookStatusChangeFailedException(request.getId());
    }

    private WishBook enrollWishBook(WishBookHandleRequest request) {
        WishBook wishBook = getWishBook(request.getId());
        checkHandled(wishBook);
        wishBook.enrollWishBook(request.getReason());

        return wishBook;
    }

    private WishBook cancelWishBook(WishBookHandleRequest request) {
        WishBook wishBook = getWishBook(request.getId());
        checkHandled(wishBook);
        wishBook.cancelWishBook(request.getReason());

        return wishBook;
    }

    private WishBook getWishBook(Long id) {
        return wishBookRepository.findById(id)
                .orElseThrow(() -> new WishBookNotFoundException(id));
    }

    private void checkHandled(WishBook wishBook) {
        if (wishBook.isHandled()) {
            throw new WishBookAlreadyHandledException(wishBook.getTitle());
        }
    }
}
