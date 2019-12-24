package com.woowacourse.tecobrary.common.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(CommonControllerAdvice.class);

    @ExceptionHandler({NumberFormatException.class,})
    public ResponseEntity handleException(final RuntimeException e, final Model model) {
        log.debug(e.getMessage());
        model.addAttribute("statusCode", 400);
        model.addAttribute("message", "잘못된 요청입니다.");
        return ResponseEntity.ok().body(model);
    }
}
