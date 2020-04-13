package com.woowacourse.tecobrary.admin.web.wishbook;

import com.woowacourse.tecobrary.admin.web.wishbook.dto.WishBookInfoResponse;
import com.woowacourse.tecobrary.admin.web.wishbook.dto.WishBookSearchRequest;
import com.woowacourse.tecobrary.admin.web.wishbook.service.WishBookAdminFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishbook")
public class WishBookAdminController {

    private final WishBookAdminFacade wishBookAdminFacade;

    @GetMapping
    Page<WishBookInfoResponse> getWishBooks(@PageableDefault Pageable pageable,
                                            @Valid WishBookSearchRequest request) {
        return wishBookAdminFacade.getWishBooks(pageable, request);
    }
}
