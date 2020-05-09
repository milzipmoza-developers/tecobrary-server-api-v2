package com.woowacourse.tecobrary.web.librarybook.service;

import com.woowacourse.tecobrary.librarybook.entity.LibraryBook;
import com.woowacourse.tecobrary.librarybook.repository.LibraryBookRepository;
import com.woowacourse.tecobrary.web.librarybook.converter.LibraryBookConverter;
import com.woowacourse.tecobrary.web.librarybook.dto.LibraryBookDetailDto;
import com.woowacourse.tecobrary.web.librarybook.exception.LibraryBookNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class LibraryBookFacade {

    private final LibraryBookRepository libraryBookRepository;
    private final LibraryBookConverter libraryBookConverter;

    public List<LibraryBookDetailDto> findAll(int page, int number) {
        List<LibraryBook> books = libraryBookRepository.findAll(PageRequest.of(page - 1, number))
                .stream()
                .collect(Collectors.toList());
        return books.stream()
                .map(libraryBookConverter::convertDetailDto)
                .collect(Collectors.toList());
    }

    public LibraryBookDetailDto findById(Long id) {
        LibraryBook libraryBook = libraryBookRepository.findById(id)
                .orElseThrow(() -> new LibraryBookNotFoundException(id));
        return libraryBookConverter.convertDetailDto(libraryBook);
    }
}
