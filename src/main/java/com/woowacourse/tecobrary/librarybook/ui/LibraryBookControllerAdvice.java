package com.woowacourse.tecobrary.librarybook.ui;

import com.woowacourse.tecobrary.librarybook.exception.DuplicatedLibraryBookException;
import com.woowacourse.tecobrary.librarybook.exception.NotFoundLibraryBookException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LibraryBookControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(LibraryBookControllerAdvice.class);

    @ExceptionHandler({DuplicatedLibraryBookException.class, NotFoundLibraryBookException.class})
    public ResponseEntity duplicatedLibraryBook(final RuntimeException e, final Model model) {
        log.error(e.getMessage());
        model.addAttribute("message", e.getMessage());
        return ResponseEntity.badRequest().body(model);
    }
}
