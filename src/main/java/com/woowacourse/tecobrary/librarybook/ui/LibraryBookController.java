package com.woowacourse.tecobrary.librarybook.ui;

import com.woowacourse.tecobrary.librarybook.application.LibraryBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LibraryBookController {

    private static final Logger log = LoggerFactory.getLogger(LibraryBookController.class);

    private final LibraryBookService libraryBookService;

    @Autowired
    public LibraryBookController(final LibraryBookService libraryBookService) {
        this.libraryBookService = libraryBookService;
    }

    @PostMapping("/books")
    public ResponseEntity createLibraryBook(@RequestBody final LibraryBookDto libraryBookDto) {
        log.debug("library book dto : {}", libraryBookDto);
        return ResponseEntity.ok(libraryBookService.save(libraryBookDto));
    }

    @GetMapping("/books/all")
    public ResponseEntity readLibraryBookTotalCount() {
        return ResponseEntity.ok(libraryBookService.count());
    }
}
