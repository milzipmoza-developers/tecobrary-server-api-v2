package com.woowacourse.tecobrary.web.wishbook;

import com.woowacourse.tecobrary.common.dto.CoreApiResponse;
import com.woowacourse.tecobrary.web.wishbook.exception.WishBookAlreadyExistException;
import com.woowacourse.tecobrary.web.wishbook.exception.WishBookRequestUserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class WishBookControllerAdvice {

    @ExceptionHandler({WishBookAlreadyExistException.class,})
    public CoreApiResponse alreadyExist(final WishBookAlreadyExistException e) {
        log.error("[WishBookControllerAdvice][alreadyExist] isbn={}, message={}", e.getIsbn(), e.getMessage());
        return CoreApiResponse.fail("400", e.getMessage());
    }

    @ExceptionHandler({WishBookRequestUserNotFoundException.class,})
    public CoreApiResponse notFoundUser(final WishBookRequestUserNotFoundException e) {
        log.error("[WishBookControllerAdvice][notFoundUser] userId={}, message={}", e.getUserId(), e.getMessage());
        return CoreApiResponse.fail("400", e.getMessage());
    }
}
