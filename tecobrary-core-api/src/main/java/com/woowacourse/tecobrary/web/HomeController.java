package com.woowacourse.tecobrary.web;

import com.woowacourse.tecobrary.common.dto.CoreApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping
public class HomeController {

    @GetMapping("/")
    public RedirectView redirect() {
        return new RedirectView("/api/v2");
    }

    @GetMapping("/api/v2")
    public CoreApiResponse index() {
        return CoreApiResponse.success("Hello. This is Tecobrary API v2. Welcome !");
    }
}
