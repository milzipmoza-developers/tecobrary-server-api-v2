package com.woowacourse.tecobrary.wishbook.ui;

import com.woowacourse.tecobrary.wishbook.command.application.WishBookEnrollService;
import com.woowacourse.tecobrary.wishbook.ui.dto.WishBookEnrollRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class WishBookEnrollController {

    private WishBookEnrollService wishBookEnrollService;

    @Autowired
    public WishBookEnrollController(final WishBookEnrollService wishBookEnrollService) {
        this.wishBookEnrollService = wishBookEnrollService;
    }

    @PatchMapping("/wishes")
    public ResponseEntity enrollLibraryBook(@RequestBody WishBookEnrollRequestDto wishBookEnrollRequestDto) {
        return ResponseEntity.ok(wishBookEnrollService.enrollLibraryBookByWishBookId(wishBookEnrollRequestDto.getId()));
    }
}
