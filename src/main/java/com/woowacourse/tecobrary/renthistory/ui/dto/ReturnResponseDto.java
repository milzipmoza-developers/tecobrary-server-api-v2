/*
 * @(#) ReturnResponseDto.java
 *
 * v 1.0.0
 *
 * 2019.12.10
 *
 * Copyright (c) 2019 woowacourse, milzipmoza-developers, thedevluffy
 * All rights reserved
 */

package com.woowacourse.tecobrary.renthistory.ui.dto;

import lombok.Getter;

@Getter
public class ReturnResponseDto extends RentHistoryResponseDto {

    private ReturnInfoDto returnInfo;

    public ReturnResponseDto(final ReturnInfoDto rentInfo, final String message) {
        super(message);
        this.returnInfo = rentInfo;
    }
}
