package com.woowacourse.tecobrary.admin.web.naverapi;

import com.woowacourse.tecobrary.admin.web.naverapi.dto.NaverApiSearchRequest;
import com.woowacourse.tecobrary.web.naver.api.NaverApiService;
import com.woowacourse.tecobrary.web.naver.dto.NaverApiItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/naver-search")
public class NaverSearchApiController {

    private final NaverApiService naverApiService;

    @GetMapping
    public Mono<List<NaverApiItemDto>> search(@Valid NaverApiSearchRequest request) {
        return naverApiService.search(request.getKeyword(), request.getPage(), request.getNumber());
    }
}
