package com.woowacourse.tecobrary.admin.web.librarybook.service;

import com.woowacourse.tecobrary.admin.web.librarybook.dto.LibraryBookSearchRequest;
import com.woowacourse.tecobrary.admin.web.librarybook.repository.LibraryBookAdminRepository;
import com.woowacourse.tecobrary.admin.web.librarybook.repository.LibraryBookSearchClause;
import com.woowacourse.tecobrary.domain.librarybook.entity.LibraryBook;
import com.woowacourse.tecobrary.domain.librarybook.repository.LibraryBookRepository;
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
}
