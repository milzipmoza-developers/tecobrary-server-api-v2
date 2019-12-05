package com.woowacourse.tecobrary.wishbook.command.application;

import com.woowacourse.tecobrary.librarybook.application.LibraryBookService;
import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookEnrollDto;
import com.woowacourse.tecobrary.librarybook.util.LibraryBookMapper;
import com.woowacourse.tecobrary.wishbook.command.domain.WishBook;
import com.woowacourse.tecobrary.wishbook.ui.dto.WishBookEnrollResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishBookEnrollService {

    private WishBookService wishBookService;
    private LibraryBookService libraryBookService;

    @Autowired
    public WishBookEnrollService(final WishBookService wishBookService, final LibraryBookService libraryBookService) {
        this.wishBookService = wishBookService;
        this.libraryBookService = libraryBookService;
    }

    public WishBookEnrollResponseDto enrollLibraryBookByWishBookId(Long wishBookId) {
        WishBook wishBook = wishBookService.findNotEnrolledById(wishBookId);
        wishBook.softDelete();
        LibraryBookEnrollDto libraryBookEnrollDto = LibraryBookMapper.toEnrollDto(wishBook);
        LibraryBookEnrollDto enrolledBook = libraryBookService.enrollWishBook(libraryBookEnrollDto);
        return new WishBookEnrollResponseDto(enrolledBook, enrolledBook.getEnrolledDate());
    }
}
