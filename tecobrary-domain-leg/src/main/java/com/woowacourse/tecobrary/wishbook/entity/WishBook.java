package com.woowacourse.tecobrary.wishbook.entity;

import com.woowacourse.tecobrary.common.domain.BookCoverUrl;
import com.woowacourse.tecobrary.common.domain.BookInfo;
import com.woowacourse.tecobrary.common.entity.DefaultBook;
import com.woowacourse.tecobrary.common.entity.DeletableEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "WishBooks")
@Access(AccessType.FIELD)
public class WishBook extends DeletableEntity implements DefaultBook {

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

    public WishBook(final BookCoverUrl wishBookCoverUrl,
                    final BookInfo wishBookInfo,
                    final WishBookRequestUser wishBookRequestUser) {
        this.wishBookCoverUrl = wishBookCoverUrl;
        this.wishBookInfo = wishBookInfo;
        this.wishBookRequestUser = wishBookRequestUser;
    }

    @Override
    public LocalDateTime softDelete() {
        return super.softDelete();
    }

    public String getImage() {
        return wishBookCoverUrl.getImage();
    }

    public String getTitle() {
        return wishBookInfo.getTitle();
    }

    public String getAuthor() {
        return wishBookInfo.getAuthor();
    }

    public String getPublisher() {
        return wishBookInfo.getPublisher();
    }

    public String getIsbn() {
        return wishBookInfo.getIsbn();
    }

    public String getDescription() {
        return wishBookInfo.getDescription();
    }

    public Long getUserId() {
        return wishBookRequestUser.getUserId();
    }
}
