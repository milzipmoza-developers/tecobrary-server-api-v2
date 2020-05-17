package com.woowacourse.tecobrary.admin.web.librarybook.service;

import com.woowacourse.tecobrary.admin.web.librarybook.dto.LibraryBookCreateRequest;
import com.woowacourse.tecobrary.admin.web.librarybook.dto.LibraryBookSearchRequest;
import com.woowacourse.tecobrary.admin.web.librarybook.repository.LibraryBookAdminRepository;
import com.woowacourse.tecobrary.admin.web.librarybook.repository.LibraryBookSearchClause;
import com.woowacourse.tecobrary.domain.librarybook.entity.LibraryBook;
import com.woowacourse.tecobrary.domain.librarybook.repository.LibraryBookRepository;
import com.woowacourse.tecobrary.domain.serial.entity.Serial;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LibraryBookAdminService {

    private final LibraryBookRepository libraryBookRepository;
    private final LibraryBookAdminRepository libraryBookAdminRepository;

    public Page<LibraryBook> getLibraryBooks(Pageable pageable, LibraryBookSearchRequest request) {
        LibraryBookSearchClause searchClause = LibraryBookSearchClause.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .publisher(request.getPublisher())
                .isbn(request.getIsbn())
                .build();

        return libraryBookAdminRepository.findAllByCondition(pageable, searchClause);
    }

    public LibraryBook findById(Long id) {
        log.info("[LibraryBookAdminService][findById] libraryBook id={}", id);
        return libraryBookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 도서입니다."));
    }

    public LibraryBook createLibraryBook(LibraryBookCreateRequest request) {
        LibraryBook libraryBook = LibraryBook.builder()
                .image(request.getImage())
                .title(request.getTitle())
                .author(request.getAuthor())
                .publisher(request.getPublisher())
                .isbn(request.getIsbn())
                .description(request.getDescription())
                .build();
        return libraryBookRepository.save(libraryBook);
    }

    public boolean deleteById(Long id) {
        LibraryBook libraryBook = findById(id);
        libraryBook.getSerialList().forEach(this::checkHasRentBook);
        libraryBookRepository.deleteById(id);
        return true;
    }

    private void checkHasRentBook(Serial serial) {
        if (serial.isRent()) {
            throw new IllegalArgumentException("대여 중인 시리얼이 존재하는 도서는 삭제할 수 없습니다.");
        }
    }
}
