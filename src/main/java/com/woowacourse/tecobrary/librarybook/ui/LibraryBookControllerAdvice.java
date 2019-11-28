package com.woowacourse.tecobrary.librarybook.ui;

import com.woowacourse.tecobrary.librarybook.exception.DuplicatedLibraryBookException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class LibraryBookControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(LibraryBookControllerAdvice.class);

    @ExceptionHandler(DuplicatedLibraryBookException.class)
    public ResponseEntity duplicatedLibraryBook(DuplicatedLibraryBookException e) {
        log.error(e.getMessage());
        Map<String, String> messages = new HashMap<>();
        messages.put("message", e.getMessage());
        return ResponseEntity.badRequest().body(messages);
    }
}
