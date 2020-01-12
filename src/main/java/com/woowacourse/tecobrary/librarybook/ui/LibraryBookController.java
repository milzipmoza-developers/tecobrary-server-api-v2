package com.woowacourse.tecobrary.librarybook.ui;

import com.woowacourse.tecobrary.librarybook.application.LibraryBookCRUDService;
import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LibraryBookController {

    private static final Logger log = LoggerFactory.getLogger(LibraryBookController.class);

    private final LibraryBookCRUDService libraryBookCRUDService;

    @Autowired
    public LibraryBookController(final LibraryBookCRUDService libraryBookCRUDService) {
        this.libraryBookCRUDService = libraryBookCRUDService;
    }

    @PostMapping("/books")
    public ResponseEntity createLibraryBook(@RequestBody final LibraryBookRequestDto libraryBookRequestDto) {
        log.debug("library book dto : {}", libraryBookRequestDto);
        return ResponseEntity.ok(libraryBookCRUDService.save(libraryBookRequestDto));
    }

    @GetMapping("/books/all")
    public ResponseEntity readLibraryBookTotalCount() {
        return ResponseEntity.ok(libraryBookCRUDService.count());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity readLibraryBook(@PathVariable final Long id) {
        return ResponseEntity.ok(libraryBookCRUDService.findById(id));
    }

    @GetMapping("/books")
    public ResponseEntity readLibraryBooks(@RequestParam final int page, @RequestParam final int number) {
        return ResponseEntity.ok(libraryBookCRUDService.findAll(page, number));
    }

    @GetMapping("/books/title/search")
    public ResponseEntity readLibraryBooksByTitle(@RequestParam final int page, @RequestParam final int number, @RequestParam final String title) {
        return ResponseEntity.ok(libraryBookCRUDService.findAllByTitleContaining(title, page, number));
    }
}
