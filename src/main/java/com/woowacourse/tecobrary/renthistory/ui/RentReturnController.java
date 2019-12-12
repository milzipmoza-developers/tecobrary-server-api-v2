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
import com.woowacourse.tecobrary.renthistory.ui.dto.ReturnRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class RentReturnController {

    private final RentReturnService rentReturnService;

    @Autowired
    public RentReturnController(final RentReturnService rentReturnService) {
        this.rentReturnService = rentReturnService;
    }

    @PostMapping("/rents")
    public ResponseEntity rentBook(@RequestBody final RentRequestDto rentRequestDto) {
        return ResponseEntity.ok(rentReturnService.rentBook(rentRequestDto));
    }

    @PatchMapping("/rents")
    public ResponseEntity returnBook(@RequestBody final ReturnRequestDto returnRequestDto) {
        return ResponseEntity.ok(rentReturnService.returnBook(returnRequestDto));
    }
}
