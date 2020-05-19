package com.woowacourse.tecobrary.web;

import com.woowacourse.tecobrary.common.dto.CoreApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class CommonControllerAdvice {

    @ExceptionHandler(Exception.class)
    public CoreApiResponse handleError(HttpServletRequest request, Exception e)   {
        log.info("[CommonControllerAdvice] exception request={}, occurred={}", request.toString(), e.getMessage());
        return CoreApiResponse.fail("400", e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public CoreApiResponse handleError404(HttpServletRequest request, Exception e)   {
        log.info("[CommonControllerAdvice] exception request={}, occurred={}", request.toString(), e.getMessage());
        return CoreApiResponse.fail("404", e.getMessage());
    }
}
