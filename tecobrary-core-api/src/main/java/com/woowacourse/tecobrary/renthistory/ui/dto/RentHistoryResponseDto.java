/*
 * @(#) RentHistoryResponseDto.java
 *
 * v 1.0.0
 *
 * 2019.12.10
 *
 * Copyright (c) 2019 woowacourse, thedevluffy
 * All rights reserved
 */

package com.woowacourse.tecobrary.renthistory.ui.dto;

import lombok.Getter;

@Getter
class RentHistoryResponseDto {

    private String message;

    RentHistoryResponseDto(final String message) {
        this.message = message;
    }
}

