package com.woowacourse.tecobrary.wishbook.ui;

import com.woowacourse.tecobrary.wishbook.command.application.WishBookService;
import com.woowacourse.tecobrary.wishbook.ui.dto.WishBookInfoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WishBookController {

    private static final Logger log = LoggerFactory.getLogger(WishBookController.class);

    private WishBookService wishBookService;

    @Autowired
    public WishBookController(final WishBookService wishBookService) {
        this.wishBookService = wishBookService;
    }

    @GetMapping("/wishes")
    public ResponseEntity findWishBooks(@RequestParam final int page, @RequestParam final int number) {
        return ResponseEntity.ok(wishBookService.findWishBooksOnPage(page, number));
    }

    @PostMapping("/wishes")
    public ResponseEntity createWishBook(@RequestBody final WishBookInfoDto wishBookInfoDto) {
        Long savedWishBookId = wishBookService.createWishBook(wishBookInfoDto);
        return ResponseEntity.ok(wishBookService.findById(savedWishBookId));
    }
}
