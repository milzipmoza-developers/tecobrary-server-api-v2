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
    public ResponseEntity createLibraryBook(@RequestBody final LibraryBookRequestDto libraryBookRequestDto) {
        log.debug("library book dto : {}", libraryBookRequestDto);
        return ResponseEntity.ok(libraryBookService.save(libraryBookRequestDto));
    }

    @GetMapping("/books/all")
    public ResponseEntity readLibraryBookTotalCount() {
        return ResponseEntity.ok(libraryBookService.count());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity readLibraryBook(@PathVariable Long id) {
        return ResponseEntity.ok(libraryBookService.findById(id));
    }
}
