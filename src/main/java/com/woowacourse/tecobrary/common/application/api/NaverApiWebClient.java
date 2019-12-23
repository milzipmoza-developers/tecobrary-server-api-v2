package com.woowacourse.tecobrary.common.application.api;

import com.woowacourse.tecobrary.common.ui.dto.NaverApiItemDto;
import com.woowacourse.tecobrary.common.ui.dto.NaverApiResponseDto;
import com.woowacourse.tecobrary.common.ui.dto.NaverSearchRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class NaverApiWebClient {

    private static final String REQUEST_SCHEME = "https";
    private static final String REQUEST_HOST = "openapi.naver.com";
    private static final String REQUEST_PATH = "v1/search/book.json";
    private static final String PARAM_QUERY = "query";
    private static final String PARAM_DISPLAY = "display";
    private static final String PARAM_START = "start";
    private static final String X_NAVER_CLIENT_ID = "X-Naver-Client-Id";
    private static final String X_NAVER_CLIENT_SECRET = "X-Naver-Client-Secret";

    private String clientId;
    private String clientSecret;

    @Autowired
    public NaverApiWebClient(@Value("${naver.client_id}") final String clientId,
                             @Value("${naver.client_secret}") final String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public Mono<List<NaverApiItemDto>> search(final NaverSearchRequestDto naverSearchRequestDto) {
        return WebClient
                .create().get().uri(uriBuilder -> uriBuilder.scheme(REQUEST_SCHEME)
                        .host(REQUEST_HOST)
                        .path(REQUEST_PATH)
                        .queryParam(PARAM_QUERY, naverSearchRequestDto.getQuery())
                        .queryParam(PARAM_DISPLAY, naverSearchRequestDto.getDisplay())
                        .queryParam(PARAM_START, naverSearchRequestDto.getStart())
                        .build())
                .header(X_NAVER_CLIENT_ID, clientId)
                .header(X_NAVER_CLIENT_SECRET, clientSecret)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(NaverApiResponseDto.class)
                .map(NaverApiResponseDto::getItems);
    }
}