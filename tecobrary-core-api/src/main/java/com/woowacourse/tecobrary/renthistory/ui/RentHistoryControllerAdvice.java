/*
 * @(#) RentHistoryControllerAdvice.java
 *
 * v 1.0.0
 *
 * 2019.12.11
 *
 * Copyright (c) 2019 woowacourse, milzipmoza-developers, thedevluffy
 * All rights reserved
 */

package com.woowacourse.tecobrary.renthistory.ui;

import com.woowacourse.tecobrary.renthistory.application.AlreadyRentBookException;
import com.woowacourse.tecobrary.renthistory.application.AlreadyReturnBookException;
import com.woowacourse.tecobrary.renthistory.application.NotFoundRentHistoryException;
import com.woowacourse.tecobrary.renthistory.domain.NotPermittedUserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;

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

    @ExceptionHandler({NotPermittedUserException.class})
    public ResponseEntity notPermittedUser(final NotPermittedUserException e, final Model model) {
        log.error(e.getMessage());
        model.addAttribute("statusCode", FORBIDDEN.value());
        model.addAttribute("message", e.getMessage());
        return ResponseEntity.status(FORBIDDEN).body(model);
    }

    @ExceptionHandler({NotFoundRentHistoryException.class})
    public ResponseEntity notFoundRentHistory(final NotFoundRentHistoryException e, final Model model) {
        log.error(e.getMessage());
        model.addAttribute("statusCode", BAD_REQUEST.value());
        model.addAttribute("returnInfo", e.getReturnRequestDto());
        model.addAttribute("message", e.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(model);
    }

    @ExceptionHandler({AlreadyReturnBookException.class})
    public ResponseEntity alreadyReturnedBook(final AlreadyReturnBookException e, final Model model) {
        model.addAttribute("statusCode", BAD_REQUEST.value());
        model.addAttribute("returnInfo", e.getReturnRequestDto());
        model.addAttribute("message", e.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(model);
    }
}
