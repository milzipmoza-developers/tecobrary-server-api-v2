package com.woowacourse.tecobrary.web.librarybook;

import com.woowacourse.tecobrary.common.dto.CoreApiResponse;
import com.woowacourse.tecobrary.web.librarybook.service.LibraryBookFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/v2")
public class LibraryBookController {

    private final LibraryBookFacade libraryBookFacade;

    @GetMapping("/books")
    public CoreApiResponse readLibraryBooks(@RequestParam final int page,
                                            @RequestParam final int number) {
        return CoreApiResponse.success(libraryBookFacade.findAll(page, number));
    }

    @GetMapping("/books/{id}")
    public CoreApiResponse readLibraryBook(@PathVariable final Long id) {
        return CoreApiResponse.success(libraryBookFacade.findById(id));
    }
}
