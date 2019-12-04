package com.woowacourse.tecobrary.serial.ui;

import com.woowacourse.tecobrary.serial.exception.NotFoundSerialNumberException;
import com.woowacourse.tecobrary.serial.exception.NotFoundSerialTargetException;
import com.woowacourse.tecobrary.serial.exception.UniqueConstraintException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SerialControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(SerialControllerAdvice.class);

    @ExceptionHandler({
            NotFoundSerialTargetException.class,
            UniqueConstraintException.class,
            NotFoundSerialNumberException.class
    })
    public ResponseEntity invalidCreateSerial(final RuntimeException e, final Model model) {
        log.error(e.getMessage());
        model.addAttribute("message", e.getMessage());
        return ResponseEntity.badRequest().body(model);
    }
}