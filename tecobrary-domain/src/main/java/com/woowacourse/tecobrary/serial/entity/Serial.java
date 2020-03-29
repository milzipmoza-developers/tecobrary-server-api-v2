package com.woowacourse.tecobrary.serial.entity;

import com.woowacourse.tecobrary.common.entity.ModifiableEntity;
import com.woowacourse.tecobrary.librarybook.entity.LibraryBook;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
public class Serial extends ModifiableEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(unique = true, nullable = false)
    private Long serialNumber;

    @Column(name = "status")
    private boolean status;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "library_book_id")
    private LibraryBook libraryBook;

    @Builder
    public Serial(Long serialNumber,
                  boolean status,
                  LibraryBook libraryBook) {
        this.serialNumber = serialNumber;
        this.status = status;
        this.libraryBook = libraryBook;
    }

    public void doRent() {
        if (this.status) {
            throw new IllegalArgumentException("이미 대여 중인 도서입니다.");
        }
        this.status = true;
    }

    public void doReturn() {
        if (!this.status) {
            throw new IllegalArgumentException("이미 반납된 도서 입니다.");
        }
        this.status = false;
    }
}
