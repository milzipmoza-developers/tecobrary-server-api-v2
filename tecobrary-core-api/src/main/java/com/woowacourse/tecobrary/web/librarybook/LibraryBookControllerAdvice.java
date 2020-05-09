package com.woowacourse.tecobrary.web.librarybook;

import com.woowacourse.tecobrary.common.dto.CoreApiResponse;
import com.woowacourse.tecobrary.web.librarybook.exception.LibraryBookNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class LibraryBookControllerAdvice {

    @ExceptionHandler({LibraryBookNotFoundException.class})
    public CoreApiResponse notFound(final RuntimeException e, final Model model) {
        log.error(e.getMessage());
        model.addAttribute("message", e.getMessage());
        return CoreApiResponse.fail("400", e.getMessage());
    }
}
