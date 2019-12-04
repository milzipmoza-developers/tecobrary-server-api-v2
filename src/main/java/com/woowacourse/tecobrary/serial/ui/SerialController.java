package com.woowacourse.tecobrary.serial.ui;

import com.woowacourse.tecobrary.serial.application.SerialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class SerialController {

    public static final String DELETE_SUCCESS_MESSAGE = "해당 일련번호를 삭제하였습니다.";

    private SerialService serialService;

    @Autowired
    public SerialController(final SerialService serialService) {
        this.serialService = serialService;
    }

    @DeleteMapping("/serials")
    public ResponseEntity deleteSerial(@RequestParam Long number, Model model) {
        serialService.deleteBySerialNumber(number);
        model.addAttribute("message", DELETE_SUCCESS_MESSAGE);
        return ResponseEntity.ok(model);
    }
}
