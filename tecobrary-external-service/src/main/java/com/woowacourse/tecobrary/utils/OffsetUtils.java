package com.woowacourse.tecobrary.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OffsetUtils {

    public static Long calculate(final Long page, final Long number) {
        long offset = 1;
        if (page > 1) {
            offset = number * (page - 1);
        }
        return offset;
    }
}