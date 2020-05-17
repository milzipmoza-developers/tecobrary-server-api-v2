package com.woowacourse.tecobrary.admin.web.librarybook.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class LibraryBookDeleteResponse {

    private Long id;
    private boolean success;
    private String message;

    @Builder
    public LibraryBookDeleteResponse(Long id, boolean success, String message) {
        this.id = id;
        this.success = success;
        this.message = message;
    }
}
