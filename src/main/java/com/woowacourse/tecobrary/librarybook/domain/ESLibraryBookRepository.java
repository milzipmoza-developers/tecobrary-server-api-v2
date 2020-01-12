package com.woowacourse.tecobrary.librarybook.domain;

import com.woowacourse.tecobrary.librarybook.application.api.ESRestClient;
import com.woowacourse.tecobrary.librarybook.exception.ESIOException;
import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookResponseDto;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.woowacourse.tecobrary.librarybook.domain.LibraryBook.*;

@Repository
public class ESLibraryBookRepository {

    private static final Logger log = LoggerFactory.getLogger(ESLibraryBookRepository.class);

    private static final Pattern ONLY_INTEGER_PATTERN = Pattern.compile("^[0-9]+$");

    private static final String LIBRARY_BOOKS_INDEX_NAME = "librarybooks";

    private static final SearchHitsMapper<LibraryBookResponseDto> LIBRARY_BOOKS_RESPONSE_DTO_MAPPER =
            new LibraryBookDtoSearchHitsMapper();

    private final ESRestClient esRestClient;

    @Autowired
    public ESLibraryBookRepository(final ESRestClient esRestClient) {
        this.esRestClient = esRestClient;
    }

    public Page<LibraryBookResponseDto> searchByKeyword(final String keyword, final Pageable pageable) {
        SearchRequest searchRequest = createSearchRequest(keyword);

        try {
            SearchResponse searchResponse = esRestClient.search(searchRequest, RequestOptions.DEFAULT);

            SearchHits searchHits = searchResponse.getHits();

            List<LibraryBookResponseDto> libraryBooks = getLibraryBookResponseDtosContainingLongId(searchHits);

            return new PageImpl<>(libraryBooks, pageable, searchHits.getTotalHits().value);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ESIOException(e);
        }
    }

    private SearchRequest createSearchRequest(final String keyword) {
        QueryBuilder queryBuilder = createQueryBuilder(keyword);

        SearchRequest searchRequest = new SearchRequest(LIBRARY_BOOKS_INDEX_NAME);
        SearchSourceBuilder searchSourceBuilder = createSearchSourceBuilder(queryBuilder);
        searchRequest.source(searchSourceBuilder);
        return searchRequest;
    }

    private BoolQueryBuilder createQueryBuilder(final String keyword) {
        return QueryBuilders.boolQuery()
                .minimumShouldMatch(1)
                .should(QueryBuilders.matchQuery(TITLE, keyword))
                .should(QueryBuilders.matchQuery(AUTHOR, keyword))
                .should(QueryBuilders.matchQuery(PUBLISHER, keyword))
                .should(QueryBuilders.matchQuery(ISBN, keyword))
                .should(QueryBuilders.matchQuery(DESCRIPTION, keyword));
    }

    private SearchSourceBuilder createSearchSourceBuilder(final QueryBuilder queryBuilder) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryBuilder);
        return searchSourceBuilder;
    }

    private List<LibraryBookResponseDto> getLibraryBookResponseDtosContainingLongId(final SearchHits searchHits) {
        return Arrays.stream(searchHits.getHits())
                .filter(ESLibraryBookRepository::containsLongId)
                .map(LIBRARY_BOOKS_RESPONSE_DTO_MAPPER::map)
                .collect(Collectors.toList());
    }

    private static boolean containsLongId(final SearchHit searchHit) {
        return ONLY_INTEGER_PATTERN.matcher(searchHit.getId()).matches();
    }
}
