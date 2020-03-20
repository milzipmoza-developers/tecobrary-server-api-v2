package com.woowacourse.tecobrary.librarybook.application;

import com.woowacourse.tecobrary.librarybook.entity.LibraryBook;
import com.woowacourse.tecobrary.librarybook.entity.LibraryBookRepository;
import com.woowacourse.tecobrary.librarybook.exception.DuplicatedLibraryBookException;
import com.woowacourse.tecobrary.librarybook.exception.NotFoundLibraryBookException;
import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookDto;
import com.woowacourse.tecobrary.librarybook.util.LibraryBookMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    public LibraryBook save(final LibraryBookDto libraryBookDto) {
        checkExistLibraryBook(libraryBookDto);
        LibraryBook libraryBook = LibraryBookMapper.toEntity(libraryBookDto);
        return libraryBookRepository.save(libraryBook);
    }

    private void checkExistLibraryBook(final LibraryBookDto libraryBookDto) {
        if (libraryBookRepository.existsByLibraryBookInfoIsbn(libraryBookDto.getIsbn())) {
            throw new DuplicatedLibraryBookException(libraryBookDto.getTitle());
        }
    }

    public Long count() {
        return libraryBookRepository.count();
    }

    public LibraryBook findById(final Long bookId) {
        return libraryBookRepository.findById(bookId)
                .orElseThrow(NotFoundLibraryBookException::new);
    }

    public List<LibraryBook> findAll(final int page, final int number) {
        return libraryBookRepository.findAll(PageRequest.of(page - 1, number))
                .stream()
                .collect(Collectors.toList());
    }

    public List<LibraryBook> findAllByTitleContaining(final String title, final int page, final int number) {
        Pageable pageable = PageRequest.of(page - 1, number);
        return libraryBookRepository.findAllByLibraryBookInfoTitleContaining(title, pageable)
                .stream()
                .collect(Collectors.toList());
    }

    public boolean existsById(final long id) {
        return libraryBookRepository.existsById(id);
    }
}
