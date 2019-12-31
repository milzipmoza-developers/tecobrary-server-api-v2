package com.woowacourse.tecobrary.librarybook.application.api;

import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;

import java.util.Collections;

public class ESRestClient extends RestHighLevelClient {

    public ESRestClient(final RestClientBuilder restClientBuilder) {
        super(restClientBuilder);
    }

    public ESRestClient(final RestClient restClient) {
        super(restClient, RestClient::close, Collections.emptyList());
    }
}
