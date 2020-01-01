package com.woowacourse.tecobrary.librarybook.domain;

import org.elasticsearch.search.SearchHit;

public interface SearchHitsMapper<T> {

    T map(SearchHit searchHit);
}
