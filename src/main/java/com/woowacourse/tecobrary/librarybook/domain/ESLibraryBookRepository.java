package com.woowacourse.tecobrary.librarybook.domain;

import com.woowacourse.tecobrary.librarybook.application.api.ESRestClient;
import com.woowacourse.tecobrary.librarybook.exception.ESIOException;
import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookResponseDto;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
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
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Repository
public class ESLibraryBookRepository {

    private static final Logger log = LoggerFactory.getLogger(ESLibraryBookRepository.class);

    private static final Pattern ONLY_INTEGER_PATTERN = Pattern.compile("^[0-9]+$");

    private static final String LIBRARY_BOOKS_INDEX_NAME = "librarybooks";
    private static final String TITLE = "title";
    private static final String AUTHOR = "author";
    private static final String PUBLISHER = "publisher";
    private static final String ISBN = "isbn";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";

    private static final SearchHitsMapper<LibraryBookResponseDto> LIBRARY_BOOKS_RESPONSE_DTO_MAPPER = searchHit -> {
        Map<String, Object> sources = searchHit.getSourceAsMap();

        return LibraryBookResponseDto.builder()
                .id(Long.valueOf(searchHit.getId()))
                .title(String.valueOf(sources.get(TITLE)))
                .author(String.valueOf(sources.get(AUTHOR)))
                .publisher(String.valueOf(sources.get(PUBLISHER)))
                .isbn(String.valueOf(sources.get(ISBN)))
                .description(String.valueOf(sources.get(DESCRIPTION)))
                .image(String.valueOf(sources.get(IMAGE)))
                .build();
    };

    private final ESRestClient esRestClient;

    @Autowired
    public ESLibraryBookRepository(final ESRestClient esRestClient) {
        this.esRestClient = esRestClient;
    }

    private static boolean containsLongId(final SearchHit searchHit) {
        return ONLY_INTEGER_PATTERN.matcher(searchHit.getId()).matches();
    }

    private SearchRequest createSearchRequest(final String keyword) {
        QueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .minimumShouldMatch(1)
                .should(QueryBuilders.matchQuery(TITLE, keyword))
                .should(QueryBuilders.matchQuery(AUTHOR, keyword))
                .should(QueryBuilders.matchQuery(PUBLISHER, keyword))
                .should(QueryBuilders.matchQuery(ISBN, keyword))
                .should(QueryBuilders.matchQuery(DESCRIPTION, keyword));

        SearchRequest searchRequest = new SearchRequest(LIBRARY_BOOKS_INDEX_NAME);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(queryBuilder);
        searchRequest.source(searchSourceBuilder);
        return searchRequest;
    }

    public Page<LibraryBookResponseDto> searchByKeyword(final String keyword, final Pageable pageable) {
        SearchRequest searchRequest = createSearchRequest(keyword);

        try {
            SearchResponse searchResponse = esRestClient.search(searchRequest, RequestOptions.DEFAULT);

            SearchHits searchHits = searchResponse.getHits();

            List<LibraryBookResponseDto> libraryBooks = Arrays.stream(searchHits.getHits())
                    .filter(ESLibraryBookRepository::containsLongId)
                    .sorted((searchHit1, searchHit2) -> Float.compare(searchHit2.getScore(), searchHit1.getScore()))
                    .map(LIBRARY_BOOKS_RESPONSE_DTO_MAPPER::map)
                    .collect(Collectors.toList());

            return new PageImpl<>(libraryBooks, pageable, searchHits.getTotalHits().value);
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new ESIOException(e);
        }
    }
}
