package com.woowacourse.tecobrary.serial.ui;

import com.woowacourse.tecobrary.serial.application.SerialCreateReadService;
import com.woowacourse.tecobrary.serial.ui.dto.SerialCreateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v2")
public class SerialCreateReadController {

    private final SerialCreateReadService serialCreateReadService;

    @Autowired
    public SerialCreateReadController(final SerialCreateReadService serialCreateReadService) {
        this.serialCreateReadService = serialCreateReadService;
    }

    @GetMapping("/serials")
    public ResponseEntity getBookSerials(@RequestParam final Long bookId) {
        return ResponseEntity.ok(serialCreateReadService.findSerialsByBookId(bookId));
    }

    @GetMapping("/serials/{serialNumber}")
    public ResponseEntity getBookBySerialNumber(@PathVariable final Long serialNumber) {
        return ResponseEntity.ok(serialCreateReadService.findBookBySerialNumber(serialNumber));
    }

    @PostMapping("/serials")
    public ResponseEntity createSerial(@RequestBody final SerialCreateRequestDto serialCreateRequestDto) {
        return ResponseEntity.ok(serialCreateReadService.save(serialCreateRequestDto));
    }
}
