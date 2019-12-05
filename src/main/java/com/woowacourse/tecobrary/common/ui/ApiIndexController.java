package com.woowacourse.tecobrary.common.ui;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class ApiIndexController {

    @GetMapping
    public ResponseEntity index() {
        return ResponseEntity.ok("Hello. This is Tecobrary API v1. Welcome !");
    }
}
