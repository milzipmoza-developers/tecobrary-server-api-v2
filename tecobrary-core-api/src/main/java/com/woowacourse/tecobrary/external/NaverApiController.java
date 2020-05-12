package com.woowacourse.tecobrary.external;

import com.woowacourse.tecobrary.external.dto.NaverApiSearchRequest;
import com.woowacourse.tecobrary.web.naver.api.NaverApiService;
import com.woowacourse.tecobrary.web.naver.dto.NaverApiItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
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

    @GetMapping("/book-search")
    public Mono<List<NaverApiItemDto>> search(@Valid NaverApiSearchRequest request) {
        return naverApiService.search(request.getKeyword(), request.getPage(), request.getNumber());
    }
}
