package com.woowacourse.tecobrary.naver;

import com.woowacourse.tecobrary.naver.dto.NaverApiItemDto;
import com.woowacourse.tecobrary.naver.dto.NaverSearchRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import com.woowacourse.tecobrary.utils.OffsetUtils;

import java.util.List;

@Service
public class NaverApiService {

    private NaverApiWebClient naverApiWebClient;

    @Autowired
    public NaverApiService(final NaverApiWebClient naverApiWebClient) {
        this.naverApiWebClient = naverApiWebClient;
    }

    public Mono<List<NaverApiItemDto>> search(final String keyword, final Long page, final Long number) {
        return naverApiWebClient.search(new NaverSearchRequestDto(keyword, number, OffsetUtils.calculate(page, number)));
    }
}
