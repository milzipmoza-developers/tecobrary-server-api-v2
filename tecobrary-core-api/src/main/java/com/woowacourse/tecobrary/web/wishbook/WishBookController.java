package com.woowacourse.tecobrary.web.wishbook;

import com.woowacourse.tecobrary.common.dto.CoreApiResponse;
import com.woowacourse.tecobrary.web.wishbook.dto.WishBookInfoDto;
import com.woowacourse.tecobrary.web.wishbook.service.WishBookFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/v2")
public class WishBookController {

    private final WishBookFacade wishBookFacade;

    @PostMapping("/wishes")
    public CoreApiResponse createWishBook(@RequestBody final WishBookInfoDto wishBookInfoDto) {
        return CoreApiResponse.success(wishBookFacade.createWishBook(wishBookInfoDto));
    }
}
