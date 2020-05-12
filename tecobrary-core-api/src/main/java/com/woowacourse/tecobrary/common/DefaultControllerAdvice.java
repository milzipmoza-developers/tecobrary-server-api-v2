package com.woowacourse.tecobrary.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class DefaultControllerAdvice {

    @ExceptionHandler({NumberFormatException.class,})
    public ResponseEntity handleException(final RuntimeException e, final Model model) {
        log.debug(e.getMessage());
        model.addAttribute("statusCode", 400);
        model.addAttribute("message", "잘못된 요청입니다.");
        return ResponseEntity.badRequest().body(model);
    }
}
