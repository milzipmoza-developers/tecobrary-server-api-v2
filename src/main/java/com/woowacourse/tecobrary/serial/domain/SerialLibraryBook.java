package com.woowacourse.tecobrary.serial.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class SerialLibraryBook {

    @Column(name = "bookId")
    private Long bookId;

    public SerialLibraryBook(Long bookId) {
        this.bookId = bookId;
    }
}
