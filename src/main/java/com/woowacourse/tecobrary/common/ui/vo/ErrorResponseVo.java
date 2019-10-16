package com.woowacourse.tecobrary.common.ui.vo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorResponseVo {
    private final String s;

    public ErrorResponseVo(String s) {
        this.s = s;
    }
}
