package com.woowacourse.tecobrary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @GetMapping("/")
    public String test() {
        log.debug("TestController >>> test method");
        return "Test Run Successfully";
    }
}
