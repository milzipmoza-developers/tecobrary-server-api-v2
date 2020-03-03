package com.woowacourse.tecobrary.wishbook.ui;

import com.woowacourse.tecobrary.wishbook.application.WishBookCRUDService;
import com.woowacourse.tecobrary.wishbook.ui.dto.WishBookInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WishBookController {

    private WishBookCRUDService wishBookCRUDService;

    @Autowired
    public WishBookController(final WishBookCRUDService wishBookCRUDService) {
        this.wishBookCRUDService = wishBookCRUDService;
    }

    @GetMapping("/wishes")
    public ResponseEntity findWishBooks(@RequestParam final int page, @RequestParam final int number) {
        return ResponseEntity.ok(wishBookCRUDService.findWishBooksOnPage(page, number));
    }

    @PostMapping("/wishes")
    public ResponseEntity createWishBook(@RequestBody final WishBookInfoDto wishBookInfoDto) {
        Long savedWishBookId = wishBookCRUDService.createWishBook(wishBookInfoDto);
        return ResponseEntity.ok(wishBookCRUDService.findById(savedWishBookId));
    }

    @DeleteMapping("/wishes")
    public ResponseEntity deleteWishBook(@RequestParam final Long id, final Model model) {
        wishBookCRUDService.deleteWishBook(id);
        model.addAttribute("message", "success");
        return ResponseEntity.ok(model);
    }
}
