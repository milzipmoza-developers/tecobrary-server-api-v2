package com.woowacourse.tecobrary.admin.web.librarybook;

import com.woowacourse.tecobrary.admin.web.librarybook.dto.*;
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

    @GetMapping("/{id}")
    public LibraryBookDescResponse getLibraryBook(@PathVariable Long id) {
        return libraryBookAdminFacade.getLibraryBook(id);
    }

    @PostMapping
    public LibraryBookDescResponse createLibraryBook(@RequestBody LibraryBookCreateRequest request) {
        return libraryBookAdminFacade.createLibraryBook(request);
    }

    @DeleteMapping("/{id}")
    public LibraryBookDeleteResponse deleteLibraryBook(@PathVariable Long id) {
        return libraryBookAdminFacade.deleteLibraryBook(id);
    }
}
