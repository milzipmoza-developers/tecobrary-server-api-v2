package com.woowacourse.tecobrary.renthistory.ui;

import com.woowacourse.tecobrary.renthistory.application.RentHistoryReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v2")
public class RentHistoryReadController {

    private final RentHistoryReadService rentHistoryReadService;

    @Autowired
    public RentHistoryReadController(final RentHistoryReadService rentHistoryReadService) {
        this.rentHistoryReadService = rentHistoryReadService;
    }

    @GetMapping("/rents/{userId}")
    public ResponseEntity findAllRentHistory(@PathVariable final Long userId) {
        return ResponseEntity.ok(rentHistoryReadService.findAllByUserId(userId));
    }

    @GetMapping("/histories/{userId}")
    public ResponseEntity findAllReturnHistory(@PathVariable final Long userId) {
        return ResponseEntity.ok(rentHistoryReadService.findAllReturnedByUserId(userId));
    }
}
