package com.woowacourse.tecobrary.wishbook.ui;

import com.woowacourse.tecobrary.wishbook.command.application.WishBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WishBookController {

    private WishBookService wishBookService;

    @Autowired
    public WishBookController(final WishBookService wishBookService) {
        this.wishBookService = wishBookService;
    }

    @GetMapping("/wishes")
    public ResponseEntity findWishBooks(@RequestParam final int page, @RequestParam final int number) {
        return ResponseEntity.ok(wishBookService.findWishBooksOnPage(page, number));
    }
}
