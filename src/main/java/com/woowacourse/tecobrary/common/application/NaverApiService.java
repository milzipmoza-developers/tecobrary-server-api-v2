package com.woowacourse.tecobrary.common.application;

import com.woowacourse.tecobrary.common.application.api.NaverApiWebClient;
import com.woowacourse.tecobrary.common.ui.dto.NaverApiItemDto;
import com.woowacourse.tecobrary.common.ui.dto.NaverSearchRequestDto;
import com.woowacourse.tecobrary.common.utils.OffsetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

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
