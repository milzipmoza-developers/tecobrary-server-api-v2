package com.woowacourse.tecobrary.librarybook.application;

import com.woowacourse.tecobrary.librarybook.domain.LibraryBook;
import com.woowacourse.tecobrary.librarybook.domain.LibraryBookRepository;
import com.woowacourse.tecobrary.librarybook.exception.DuplicatedLibraryBookException;
import com.woowacourse.tecobrary.librarybook.exception.NotFoundLibraryBookException;
import com.woowacourse.tecobrary.librarybook.ui.dto.*;
import com.woowacourse.tecobrary.librarybook.util.LibraryBookMapper;
import com.woowacourse.tecobrary.serial.domain.SerialLibraryBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryBookService {

    private final LibraryBookRepository libraryBookRepository;

    @Autowired
    public LibraryBookService(final LibraryBookRepository libraryBookRepository) {
        this.libraryBookRepository = libraryBookRepository;
    }

    public LibraryBookCreateResponseDto save(final LibraryBookDto libraryBookDto) {
        LibraryBook savedLibraryBook = getCreatedLibraryBook(libraryBookDto);
        return new LibraryBookCreateResponseDto(savedLibraryBook.getId(), savedLibraryBook.getTitle() + " register succeed");
    }

    private LibraryBook getCreatedLibraryBook(final LibraryBookDto libraryBookDto) {
        checkExistLibraryBook(libraryBookDto);
        LibraryBook libraryBook = LibraryBookMapper.toEntity(libraryBookDto);
        return libraryBookRepository.save(libraryBook);
    }

    public LibraryBookEnrollDto enrollWishBook(final LibraryBookDto libraryBookDto) {
        LibraryBook enrolledBook = getCreatedLibraryBook(libraryBookDto);
        return LibraryBookMapper.toEnrolledDto(enrolledBook);
    }

    private void checkExistLibraryBook(final LibraryBookDto libraryBookDto) {
        if (libraryBookRepository.existsByLibraryBookInfoIsbn(libraryBookDto.getIsbn())) {
            throw new DuplicatedLibraryBookException(libraryBookDto.getTitle());
        }
    }

    public LibraryBookTotalCountResponseDto count() {
        return new LibraryBookTotalCountResponseDto(libraryBookRepository.count());
    }

    public LibraryBookResponseDto findById(final Long id) {
        LibraryBook libraryBook = libraryBookRepository.findById(id).orElseThrow(NotFoundLibraryBookException::new);
        return LibraryBookMapper.toDto(libraryBook);
    }

    public List<LibraryBookResponseDto> findAll(final int page, final int number) {
        Page<LibraryBook> libraryBooks = libraryBookRepository.findAll(PageRequest.of(page - 1, number));
        return libraryBooks.stream()
                .map(LibraryBookMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<LibraryBookResponseDto> findAllByTitleContaining(final String title, final int page, final int number) {
        Pageable pageable = PageRequest.of(page - 1, number);
        Page<LibraryBook> libraryBooks = libraryBookRepository.findAllByLibraryBookInfoTitleContaining(title, pageable);
        return libraryBooks.stream()
                .map(LibraryBookMapper::toDto)
                .collect(Collectors.toList());
    }

    public boolean existsById(final long id) {
        return libraryBookRepository.existsById(id);
    }

    public LibraryBook findBySerialLibraryBook(final SerialLibraryBook serialLibraryBook) {
        return libraryBookRepository.findById(serialLibraryBook.getBookId())
                .orElseThrow(NotFoundLibraryBookException::new);
    }
}
