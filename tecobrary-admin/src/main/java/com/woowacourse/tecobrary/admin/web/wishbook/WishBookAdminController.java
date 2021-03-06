package com.woowacourse.tecobrary.admin.web.wishbook;

import com.woowacourse.tecobrary.admin.web.wishbook.dto.*;
import com.woowacourse.tecobrary.admin.web.wishbook.service.WishBookAdminFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishbook")
public class WishBookAdminController {

    private final WishBookAdminFacade wishBookAdminFacade;

    @GetMapping
    public Page<WishBookInfoResponse> getWishBooks(@PageableDefault Pageable pageable,
                                                   @Valid WishBookSearchRequest request) {
        return wishBookAdminFacade.getWishBooks(pageable, request);
    }

    @PostMapping
    public WishBookHandleResponse enrollWishBook(@RequestHeader("Send-Tecorvis-Message") boolean sendMessage,
                                                 @RequestBody WishBookEnrollRequest request) {
        return wishBookAdminFacade.enrollWishBook(sendMessage, request);
    }

    @PutMapping
    public WishBookHandleResponse cancelWishBook(@RequestBody WishBookCancelRequest request) {
        return wishBookAdminFacade.cancelWishBook(request);
    }
}
