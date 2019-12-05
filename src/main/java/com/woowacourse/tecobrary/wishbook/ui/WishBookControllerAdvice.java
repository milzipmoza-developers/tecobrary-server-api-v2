package com.woowacourse.tecobrary.wishbook.ui;

import com.woowacourse.tecobrary.wishbook.command.application.AlreadySoftDeletedWishBookException;
import com.woowacourse.tecobrary.wishbook.command.domain.DuplicatedWishBookIsbnException;
import com.woowacourse.tecobrary.wishbook.command.domain.NotFoundWishBookException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WishBookControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(WishBookControllerAdvice.class);

    @ExceptionHandler({
            NotFoundWishBookException.class,
            AlreadySoftDeletedWishBookException.class,
            DuplicatedWishBookIsbnException.class
    })
    public ResponseEntity wishBookHandlingException(final RuntimeException e, final Model model) {
        log.error(e.getMessage());
        model.addAttribute("message", e.getMessage());
        return ResponseEntity.badRequest().body(model);
    }
}
