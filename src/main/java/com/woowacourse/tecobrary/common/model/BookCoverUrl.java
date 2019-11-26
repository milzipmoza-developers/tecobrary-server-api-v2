package com.woowacourse.tecobrary.common.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class BookCoverUrl {

    @Column(name = "image")
    private String image;

    public BookCoverUrl(String image) {
        this.image = image;
    }
}
