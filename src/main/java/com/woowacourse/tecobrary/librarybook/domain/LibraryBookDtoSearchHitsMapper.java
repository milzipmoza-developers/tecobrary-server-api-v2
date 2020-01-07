package com.woowacourse.tecobrary.librarybook.domain;

import com.woowacourse.tecobrary.librarybook.ui.dto.LibraryBookResponseDto;
import org.elasticsearch.search.SearchHit;

import java.util.Map;

import static com.woowacourse.tecobrary.librarybook.domain.LibraryBook.*;

public class LibraryBookDtoSearchHitsMapper implements SearchHitsMapper<LibraryBookResponseDto> {

    @Override
    public LibraryBookResponseDto map(final SearchHit searchHit) {
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
    }
}
