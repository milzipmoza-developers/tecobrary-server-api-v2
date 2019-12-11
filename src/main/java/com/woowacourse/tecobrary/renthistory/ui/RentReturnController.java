/*
 * @(#) RentReturnController.java
 *
 * v 1.0.0
 *
 * 2019.12.10
 *
 * Copyright (c) 2019 woowacourse, milzipmoza-developers, thedevluffy
 * All rights reserved
 */

package com.woowacourse.tecobrary.renthistory.ui;

import com.woowacourse.tecobrary.renthistory.application.RentReturnService;
import com.woowacourse.tecobrary.renthistory.ui.dto.RentRequestDto;
import com.woowacourse.tecobrary.renthistory.ui.dto.RentResponseDto;
import com.woowacourse.tecobrary.renthistory.ui.dto.ReturnRequestDto;
import com.woowacourse.tecobrary.renthistory.ui.dto.ReturnResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class RentReturnController {

    private static final String RENT_SUCCESS_MESSAGE = "대여에 성공하였습니다.";
    private static final String RETURN_SUCCESS_MESSAGE = "반납에 성공하였습니다.";

    private final RentReturnService rentReturnService;

    @Autowired
    public RentReturnController(final RentReturnService rentReturnService) {
        this.rentReturnService = rentReturnService;
    }

    @PostMapping("/rents")
    public ResponseEntity rent(@RequestBody final RentRequestDto rentRequestDto) {
        return ResponseEntity.ok(new RentResponseDto(
                rentReturnService.rent(rentRequestDto),
                RENT_SUCCESS_MESSAGE)
        );
    }

    @PatchMapping("/rents")
    public ResponseEntity returnBook(@RequestBody final ReturnRequestDto returnRequestDto) {
        return ResponseEntity.ok(new ReturnResponseDto(
                rentReturnService.returnBook(returnRequestDto),
                RETURN_SUCCESS_MESSAGE)
        );
    }
}
