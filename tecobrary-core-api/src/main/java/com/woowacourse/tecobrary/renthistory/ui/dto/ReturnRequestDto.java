/*
 * @(#) ReturnRequestDto.java
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
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class ReturnRequestDto extends RentHistoryRequestDto {

    public ReturnRequestDto(final Long serial, final Long userId) {
        super(serial, userId);
    }
}
