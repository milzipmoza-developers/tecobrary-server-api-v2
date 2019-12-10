package com.woowacourse.tecobrary.renthistory.ui;

import com.woowacourse.tecobrary.renthistory.application.RentHistoryReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class RentHistoryReadController {

    private final RentHistoryReadService rentHistoryReadService;

    @Autowired
    public RentHistoryReadController(final RentHistoryReadService rentHistoryReadService) {
        this.rentHistoryReadService = rentHistoryReadService;
    }

    @GetMapping("/rents/{userId}")
    public ResponseEntity findAllRentHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(rentHistoryReadService.findAllByUserId(userId));
    }
}
