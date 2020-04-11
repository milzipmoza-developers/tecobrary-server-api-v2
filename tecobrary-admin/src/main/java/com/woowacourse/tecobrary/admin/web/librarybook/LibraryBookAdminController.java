package com.woowacourse.tecobrary.admin.web.librarybook;

import com.woowacourse.tecobrary.admin.web.librarybook.dto.LibraryBookDescResponse;
import com.woowacourse.tecobrary.admin.web.librarybook.dto.LibraryBookInfoResponse;
import com.woowacourse.tecobrary.admin.web.librarybook.dto.LibraryBookSearchRequest;
import com.woowacourse.tecobrary.admin.web.librarybook.service.LibraryBookAdminFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/librarybook")
public class LibraryBookAdminController {

    private final LibraryBookAdminFacade libraryBookAdminFacade;

    @GetMapping
    public Page<LibraryBookInfoResponse> getLibraryBooks(@PageableDefault Pageable pageable,
                                                         @Valid LibraryBookSearchRequest request) {
        return libraryBookAdminFacade.getLibraryBooks(pageable, request);
    }

    // 책 상세 조회
    @GetMapping("/{id}")
    public LibraryBookDescResponse getLibraryBook(@PathVariable Long id) {
        return libraryBookAdminFacade.getLibraryBook(id);
    }

    // 책 생성
    @PostMapping
    public LibraryBookDescResponse createLibraryBook() {
        return null;
    }

    // 책 제거
    @DeleteMapping("/{id}")
    public LibraryBookDescResponse deleteLibraryBook() {
        return null;
    }
}
