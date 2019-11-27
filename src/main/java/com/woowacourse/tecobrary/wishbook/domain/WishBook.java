package com.woowacourse.tecobrary.wishbook.domain;

import com.woowacourse.tecobrary.common.model.BookCoverUrl;
import com.woowacourse.tecobrary.common.model.BookInfo;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "WishBooks")
@Access(AccessType.FIELD)
public class WishBook {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Embedded
    private BookCoverUrl wishBookCoverUrl;

    @Embedded
    private BookInfo wishBookInfo;

    @Embedded
    private WishBookRequestUser wishBookRequestUser;

    public WishBook(BookCoverUrl wishBookCoverUrl, BookInfo wishBookInfo, WishBookRequestUser wishBookRequestUser) {
        this.wishBookCoverUrl = wishBookCoverUrl;
        this.wishBookInfo = wishBookInfo;
        this.wishBookRequestUser = wishBookRequestUser;
    }
}
