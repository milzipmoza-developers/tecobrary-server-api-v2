package com.woowacourse.tecobrary.renthistory.ui;

import com.woowacourse.tecobrary.renthistory.application.RentReturnService;
import com.woowacourse.tecobrary.renthistory.ui.dto.RentRequestDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class RentReturnController {

    private static final Logger log = LoggerFactory.getLogger(RentReturnController.class);

    private final RentReturnService rentReturnService;

    @Autowired
    public RentReturnController(final RentReturnService rentReturnService) {
        this.rentReturnService = rentReturnService;
    }

    @PostMapping("/rents")
    public ResponseEntity rent(@RequestBody final RentRequestDto rentRequestDto) {
        log.debug("rentRequestDto : {}", rentRequestDto);
        return ResponseEntity.ok(rentReturnService.rent(rentRequestDto));
    }
}
