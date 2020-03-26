package com.woowacourse.tecobrary.common.ui;

import com.woowacourse.tecobrary.web.naver.api.NaverApiService;
import com.woowacourse.tecobrary.web.naver.dto.NaverApiItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v2")
public class NaverApiController {

    private NaverApiService naverApiService;

    @Autowired
    public NaverApiController(final NaverApiService naverApiService) {
        this.naverApiService = naverApiService;
    }

    @GetMapping("/naverapi")
    public Mono<List<NaverApiItemDto>> search(@RequestParam final String keyword,
                                              @RequestParam final Long page,
                                              @RequestParam final Long number) {
        return naverApiService.search(keyword, page, number);
    }
}
