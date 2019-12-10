package com.woowacourse.tecobrary.renthistory.ui;

import com.woowacourse.tecobrary.renthistory.application.AlreadyRentBookException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RentHistoryControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(RentHistoryControllerAdvice.class);

    @ExceptionHandler({AlreadyRentBookException.class})
    public ResponseEntity notFoundUser(final AlreadyRentBookException e, final Model model) {
        log.error(e.getMessage());
        model.addAttribute("rentInfo", e.getRentRequestDto());
        model.addAttribute("message", e.getMessage());
        return ResponseEntity.badRequest().body(model);
    }
}
