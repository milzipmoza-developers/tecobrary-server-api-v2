package com.woowacourse.tecobrary.librarybook.application;

import com.woowacourse.tecobrary.librarybook.domain.LibraryBook;
import com.woowacourse.tecobrary.librarybook.domain.LibraryBookRepository;
import com.woowacourse.tecobrary.librarybook.exception.DuplicatedLibraryBookException;
import com.woowacourse.tecobrary.librarybook.ui.LibraryBookCreateResponseDto;
import com.woowacourse.tecobrary.librarybook.ui.LibraryBookDto;
import com.woowacourse.tecobrary.librarybook.ui.LibraryBookTotalCountResponseDto;
import com.woowacourse.tecobrary.librarybook.util.LibraryBookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryBookService {

    private final LibraryBookRepository libraryBookRepository;

    @Autowired
    public LibraryBookService(final LibraryBookRepository libraryBookRepository) {
        this.libraryBookRepository = libraryBookRepository;
    }

    public LibraryBookCreateResponseDto save(final LibraryBookDto libraryBookDto) {
        checkExistLibraryBook(libraryBookDto);

        LibraryBook libraryBook = LibraryBookMapper.map(libraryBookDto);
        LibraryBook savedLibraryBook = libraryBookRepository.save(libraryBook);
        return new LibraryBookCreateResponseDto(savedLibraryBook.getId(), savedLibraryBook.getTitle() + " register succeed");
    }

    private void checkExistLibraryBook(final LibraryBookDto libraryBookDto) {
        if (libraryBookRepository.existsByLibraryBookInfoIsbn(libraryBookDto.getIsbn())) {
            throw new DuplicatedLibraryBookException(libraryBookDto.getTitle());
        }
    }

    public LibraryBookTotalCountResponseDto count() {
        return new LibraryBookTotalCountResponseDto(libraryBookRepository.count());
    }
}
