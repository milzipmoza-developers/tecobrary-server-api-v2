package com.woowacourse.tecobrary.librarybook.ui;

import com.woowacourse.tecobrary.librarybook.domain.LibraryBook;
import com.woowacourse.tecobrary.librarybook.domain.LibraryBookRepository;
import com.woowacourse.tecobrary.librarybook.util.LibraryBookMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class LibraryBookController {

    private static final Logger log = LoggerFactory.getLogger(LibraryBookController.class);

    private final LibraryBookRepository libraryBookRepository;

    @Autowired
    public LibraryBookController(final LibraryBookRepository libraryBookRepository) {
        this.libraryBookRepository = libraryBookRepository;
    }

    @PostMapping("/books")
    public ResponseEntity createLibraryBook(@RequestBody LibraryBookDto libraryBookDto) {
        log.debug("library book dto : {}", libraryBookDto);
        LibraryBook libraryBook = LibraryBookMapper.map(libraryBookDto);
        LibraryBook savedLibraryBook = libraryBookRepository.save(libraryBook);
        return ResponseEntity.ok(new LibraryBookCreateResponseDto(savedLibraryBook.getId(), savedLibraryBook.getLibraryBookInfo().getTitle() + " register succeed"));
    }
}
