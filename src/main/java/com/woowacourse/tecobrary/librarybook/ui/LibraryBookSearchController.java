package com.woowacourse.tecobrary.librarybook.ui;

import com.woowacourse.tecobrary.librarybook.application.ESLibraryBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class LibraryBookSearchController {

    private final ESLibraryBookService esLibraryBookService;

    @Autowired
    public LibraryBookSearchController(final ESLibraryBookService esLibraryBookService) {
        this.esLibraryBookService = esLibraryBookService;
    }

    @GetMapping("/book/search")
    public ResponseEntity searchLibraryBooks(@RequestParam final String keyword,
                                             @RequestParam final int page, @RequestParam final int size) {
        return ResponseEntity.ok(esLibraryBookService.searchLibraryBook(keyword, page, size));
    }
}
