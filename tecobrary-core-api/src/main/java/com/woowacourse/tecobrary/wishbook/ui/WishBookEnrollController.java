package com.woowacourse.tecobrary.wishbook.ui;

import com.woowacourse.tecobrary.wishbook.application.WishBookEnrollService;
import com.woowacourse.tecobrary.wishbook.ui.dto.WishBookEnrollRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v2")
public class WishBookEnrollController {

    private WishBookEnrollService wishBookEnrollService;

    @Autowired
    public WishBookEnrollController(final WishBookEnrollService wishBookEnrollService) {
        this.wishBookEnrollService = wishBookEnrollService;
    }

    @PatchMapping("/wishes")
    public ResponseEntity enrollLibraryBook(@RequestBody final WishBookEnrollRequestDto wishBookEnrollRequestDto) {
        return ResponseEntity.ok(wishBookEnrollService.enrollLibraryBookByWishBookId(wishBookEnrollRequestDto.getId()));
    }
}
