package com.woowacourse.tecobrary.web.serial;

import com.woowacourse.tecobrary.common.dto.CoreApiResponse;
import com.woowacourse.tecobrary.web.serial.service.SerialFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/v2")
public class SerialController {

    private final SerialFacade serialFacade;

    @GetMapping("/serials")
    public CoreApiResponse getBookSerials(@RequestParam final Long bookId) {
        return CoreApiResponse.success(serialFacade.findSerialsByBookId(bookId));
    }

    @GetMapping("/serials/{serialNumber}")
    public CoreApiResponse getBookBySerialNumber(@PathVariable final Long serialNumber) {
        return CoreApiResponse.success(serialFacade.findBookBySerialNumber(serialNumber));
    }
}
