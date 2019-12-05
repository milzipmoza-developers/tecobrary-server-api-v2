package com.woowacourse.tecobrary.wishbook.command.domain;

import com.woowacourse.tecobrary.common.model.BookCoverUrl;
import com.woowacourse.tecobrary.common.model.BookInfo;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @CreationTimestamp
    @Column(name = "createdAt",
            updatable = false,
            nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt",
            nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "deletedAt",
            columnDefinition = "DATETIME default NULL")
    private LocalDateTime deletedAt;

    public WishBook(BookCoverUrl wishBookCoverUrl, BookInfo wishBookInfo, WishBookRequestUser wishBookRequestUser) {
        this.wishBookCoverUrl = wishBookCoverUrl;
        this.wishBookInfo = wishBookInfo;
        this.wishBookRequestUser = wishBookRequestUser;
    }

    public LocalDateTime softDelete() {
        return setDeletedAt(LocalDateTime.now());
    }

    private LocalDateTime setDeletedAt(final LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
        return this.deletedAt;
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
