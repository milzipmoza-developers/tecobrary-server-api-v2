/*
 * @(#) ReturnInfoDto.java
 *
 * v 1.0.0
 *
 * 2019.12.11
 *
 * Copyright (c) 2019 woowacourse, milzipmoza-developers, thedevluffy
 * All rights reserved
 */
package com.woowacourse.tecobrary.renthistory.ui.dto;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class ReturnInfoDto {

    private String title;
    private Long serialNumber;
    private LocalDateTime returnedAt;

    public ReturnInfoDto(final String title, final Long serialNumber, final LocalDateTime returnedAt) {
        this.title = title;
        this.serialNumber = serialNumber;
        this.returnedAt = returnedAt;
    }
}
