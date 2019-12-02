package com.woowacourse.tecobrary.librarybook.application;

import com.woowacourse.tecobrary.librarybook.domain.LibraryBook;
import com.woowacourse.tecobrary.librarybook.domain.LibraryBookRepository;
import com.woowacourse.tecobrary.librarybook.exception.DuplicatedLibraryBookException;
import com.woowacourse.tecobrary.librarybook.exception.NotFoundLibraryBookException;
import com.woowacourse.tecobrary.librarybook.ui.LibraryBookCreateResponseDto;
import com.woowacourse.tecobrary.librarybook.ui.LibraryBookRequestDto;
import com.woowacourse.tecobrary.librarybook.ui.LibraryBookResponseDto;
import com.woowacourse.tecobrary.librarybook.ui.LibraryBookTotalCountResponseDto;
import com.woowacourse.tecobrary.librarybook.util.LibraryBookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public LibraryBookCreateResponseDto save(final LibraryBookRequestDto libraryBookRequestDto) {
        checkExistLibraryBook(libraryBookRequestDto);

        LibraryBook libraryBook = LibraryBookMapper.toEntity(libraryBookRequestDto);
        LibraryBook savedLibraryBook = libraryBookRepository.save(libraryBook);
        return new LibraryBookCreateResponseDto(savedLibraryBook.getId(), savedLibraryBook.getTitle() + " register succeed");
    }

    private void checkExistLibraryBook(final LibraryBookRequestDto libraryBookRequestDto) {
        if (libraryBookRepository.existsByLibraryBookInfoIsbn(libraryBookRequestDto.getIsbn())) {
            throw new DuplicatedLibraryBookException(libraryBookRequestDto.getTitle());
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
}
