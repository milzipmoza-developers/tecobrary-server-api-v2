package com.woowacourse.tecobrary.domain.wishbook.entity;

import com.woowacourse.tecobrary.domain.common.entity.ModifiableBaseEntity;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
public class WishBookHistory extends ModifiableBaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private WishBookStatus status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "wish_book_id")
    private WishBook wishBook;

    @Builder
    public WishBookHistory(String reason, WishBookStatus status, WishBook wishBook) {
        this.reason = reason;
        this.status = status;
        this.wishBook = wishBook;
    }
}
