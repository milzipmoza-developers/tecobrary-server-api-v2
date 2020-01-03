package com.woowacourse.tecobrary.librarybook.domain;

import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookResponseDto;
import org.elasticsearch.search.SearchHit;

import java.util.Map;

public class LibraryBookDtoSearchHitsMapper implements SearchHitsMapper<LibraryBookResponseDto> {

    @Override
    public LibraryBookResponseDto map(final SearchHit searchHit) {
        Map<String, Object> sources = searchHit.getSourceAsMap();

        return LibraryBookResponseDto.builder()
                .id(Long.valueOf(searchHit.getId()))
                .title(String.valueOf(sources.get("title")))
                .author(String.valueOf(sources.get("author")))
                .publisher(String.valueOf(sources.get("publisher")))
                .isbn(String.valueOf(sources.get("isbn")))
                .description(String.valueOf(sources.get("description")))
                .image(String.valueOf(sources.get("image")))
                .build();
    }
}
