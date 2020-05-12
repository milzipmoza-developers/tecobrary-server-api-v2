package com.woowacourse.tecobrary.web.renthistory;

import com.woowacourse.tecobrary.common.dto.CoreApiResponse;
import com.woowacourse.tecobrary.web.renthistory.dto.RentRequest;
import com.woowacourse.tecobrary.web.renthistory.dto.ReturnRequest;
import com.woowacourse.tecobrary.web.renthistory.service.RentHistoryFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/v2")
public class RentHistoryController {

    private final RentHistoryFacade rentHistoryFacade;

    @GetMapping("/rents/{userId}")
    public CoreApiResponse findAllRentHistory(@PathVariable final Long userId) {
        return CoreApiResponse.success(rentHistoryFacade.findAllByUserId(userId));
    }

    @GetMapping("/histories/{userId}")
    public CoreApiResponse findAllReturnHistory(@PathVariable final Long userId) {
        return CoreApiResponse.success(rentHistoryFacade.findAllReturnedByUserId(userId));
    }

    @PostMapping("/rents")
    public CoreApiResponse rentBook(@RequestBody final RentRequest requestDto) {
        return CoreApiResponse.success(rentHistoryFacade.rentBook(requestDto));
    }

    @PatchMapping("/return")
    public CoreApiResponse returnBook(@RequestBody final ReturnRequest requestDto) {
        return CoreApiResponse.success(rentHistoryFacade.returnBook(requestDto));
    }
}
