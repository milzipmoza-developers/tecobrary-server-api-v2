/*
 * @(#) RentResponseDto.java
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
public class RentResponseDto extends RentHistoryResponseDto {

    private RentInfoDto rentInfo;

    public RentResponseDto(final RentInfoDto rentInfo, final String message) {
        super(message);
        this.rentInfo = rentInfo;
    }
}
