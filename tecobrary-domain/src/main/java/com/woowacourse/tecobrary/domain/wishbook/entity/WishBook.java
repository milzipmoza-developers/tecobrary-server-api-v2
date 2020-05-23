package com.woowacourse.tecobrary.domain.wishbook.entity;

import com.woowacourse.tecobrary.domain.admin.entity.Admin;
import com.woowacourse.tecobrary.domain.common.entity.DeletableEntity;
import com.woowacourse.tecobrary.domain.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
public class WishBook extends DeletableEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false, length = 150)
    private String image;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 50)
    private String author;

    @Column(nullable = false, length = 20)
    private String publisher;

    @Column(unique = true, nullable = false, length = 30)
    private String isbn;

    @Column(length = 500,
            columnDefinition = "varchar(500) default '내용 없음'")
    private String description;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private WishBookStatus status;

    @Column(nullable = false, length = 100)
    private String reason;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "wish_book_request_user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "wishBook")
    private List<WishBookHistory> wishBookHistories = new ArrayList<>();

    @Builder
    public WishBook(String image,
                    String title,
                    String author,
                    String publisher,
                    String isbn,
                    String description,
                    String reason,
                    User user) {
        this.image = image;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.description = description;
        this.status = WishBookStatus.REQUESTED;
        this.reason = reason;
        this.user = user;

        logHistory("신규 등록", this.status);
    }

    public boolean isHandled() {
        return status.isHandled();
    }

    public void enrollWishBook(String reason) {
        handleWishBook(reason, WishBookStatus.ENROLLED);
    }

    public void cancelWishBook(String reason) {
        handleWishBook(reason, WishBookStatus.CANCELED);
    }

    public boolean isEnrolled() {
        return this.status == WishBookStatus.ENROLLED;
    }

    @Override
    protected LocalDateTime softDelete() {
        return super.softDelete();
    }

    private void handleWishBook(String reason, WishBookStatus status) {
        this.status = status;
        this.reason = reason;
        this.softDelete();

        logHistory(reason, status);
    }

    private void logHistory(String reason, WishBookStatus status) {
        wishBookHistories.add(WishBookHistory.builder()
                .wishBook(this)
                .reason(reason)
                .status(status)
                .build());
    }
}
