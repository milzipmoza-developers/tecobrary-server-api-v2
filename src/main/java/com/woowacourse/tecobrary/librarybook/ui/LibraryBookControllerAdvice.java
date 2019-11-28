package com.woowacourse.tecobrary.librarybook.ui;

import com.woowacourse.tecobrary.librarybook.exception.DuplicatedLibraryBookException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LibraryBookControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(LibraryBookControllerAdvice.class);

    @ExceptionHandler(DuplicatedLibraryBookException.class)
    public ResponseEntity duplicatedLibraryBook(DuplicatedLibraryBookException e, Model model) {
        log.error(e.getMessage());
        model.addAttribute("message", e.getMessage());
        return ResponseEntity.badRequest().body(model);
    }
}
