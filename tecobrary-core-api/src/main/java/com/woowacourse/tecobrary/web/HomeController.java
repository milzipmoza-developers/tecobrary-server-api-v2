package com.woowacourse.tecobrary.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2")
public class HomeController {

    @GetMapping
    public ResponseEntity index() {
        return ResponseEntity.ok("Hello. This is Tecobrary API v2. Welcome !");
    }
}
