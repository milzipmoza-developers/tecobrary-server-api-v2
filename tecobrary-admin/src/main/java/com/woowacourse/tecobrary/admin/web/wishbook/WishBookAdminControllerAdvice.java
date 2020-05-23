package com.woowacourse.tecobrary.admin.web.wishbook;

import com.woowacourse.tecobrary.admin.web.wishbook.exception.WishBookAlreadyHandledException;
import com.woowacourse.tecobrary.admin.web.wishbook.exception.WishBookNotFoundException;
import com.woowacourse.tecobrary.admin.web.wishbook.exception.WishBookStatusChangeFailedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class WishBookAdminControllerAdvice {

    @ExceptionHandler(WishBookNotFoundException.class)
    public ResponseEntity handleError(HttpServletRequest request, WishBookNotFoundException e)   {
        log.info("[WishBookAdminControllerAdvice] exception request={}, occurred={}", request.toString(), e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(WishBookAlreadyHandledException.class)
    public ResponseEntity handleError(HttpServletRequest request, WishBookAlreadyHandledException e)   {
        log.info("[WishBookAdminControllerAdvice] exception request={}, occurred={}", request.toString(), e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(WishBookStatusChangeFailedException.class)
    public ResponseEntity handleError(HttpServletRequest request, WishBookStatusChangeFailedException e)   {
        log.info("[WishBookAdminControllerAdvice] exception request={}, occurred={}", request.toString(), e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
