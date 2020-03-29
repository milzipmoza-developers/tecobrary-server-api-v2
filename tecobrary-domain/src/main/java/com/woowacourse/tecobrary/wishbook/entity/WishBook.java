package com.woowacourse.tecobrary.wishbook.entity;

import com.woowacourse.tecobrary.common.entity.DeletableEntity;
import com.woowacourse.tecobrary.user.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "wish_book_request_user_id")
    private User user;

    @Builder
    public WishBook(String image,
                    String title,
                    String author,
                    String publisher,
                    String isbn,
                    String description,
                    User user) {
        this.image = image;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.description = description;
        this.user = user;
    }

    @Override
    protected LocalDateTime softDelete() {
        return super.softDelete();
    }
}
