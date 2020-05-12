package com.woowacourse.tecobrary.common.entity;

import java.time.LocalDateTime;

public interface DefaultBook {

    String getTitle();
    String getAuthor();
    String getPublisher();
    String getIsbn();
    String getDescription();
    String getImage();
    LocalDateTime getCreatedAt();
}
