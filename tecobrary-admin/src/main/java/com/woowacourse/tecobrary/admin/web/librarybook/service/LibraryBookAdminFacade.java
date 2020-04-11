package com.woowacourse.tecobrary.admin.web.librarybook.service;

import com.woowacourse.tecobrary.admin.web.librarybook.converter.LibraryBookConverter;
import com.woowacourse.tecobrary.admin.web.librarybook.dto.*;
import com.woowacourse.tecobrary.domain.librarybook.entity.LibraryBook;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class LibraryBookAdminFacade {

    private final LibraryBookAdminService libraryBookAdminService;
    private final LibraryBookConverter libraryBookConverter;

    public Page<LibraryBookInfoResponse> getLibraryBooks(Pageable pageable,
                                                         LibraryBookSearchRequest request) {
        Page<LibraryBook> libraryBooks = libraryBookAdminService.getLibraryBooks(pageable, request);

        List<LibraryBookInfoResponse> dtos = libraryBooks.stream()
                .map(libraryBookConverter::convertInfo)
                .collect(Collectors.toList());

        return new PageImpl<>(dtos, pageable, libraryBooks.getTotalElements());
    }

    public LibraryBookDescResponse getLibraryBook(Long id) {
        LibraryBook libraryBook = libraryBookAdminService.findById(id);
        return libraryBookConverter.convertDesc(libraryBook);
    }

    public LibraryBookDescResponse createLibraryBook(LibraryBookCreateRequest request) {
        LibraryBook libraryBook = libraryBookAdminService.createLibraryBook(request);
        return libraryBookConverter.convertDesc(libraryBook);
    }

    public LibraryBookDeleteResponse deleteLibraryBook(Long id) {
        boolean result = libraryBookAdminService.deleteById(id);
        return LibraryBookDeleteResponse.builder()
                .id(id)
                .message("삭제 성공")
                .success(result)
                .build();
    }
}
