package com.woowacourse.tecobrary.common.ui.vo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorResponseVo {
    private final String message;
    private final String description;

    public ErrorResponseVo(String message, String description) {
        this.message = message;
        this.description = description;
    }
}
