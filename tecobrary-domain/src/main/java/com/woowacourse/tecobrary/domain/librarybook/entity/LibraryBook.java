package com.woowacourse.tecobrary.domain.librarybook.entity;

import com.woowacourse.tecobrary.domain.common.entity.ModifiableEntity;
import com.woowacourse.tecobrary.domain.serial.entity.Serial;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
public class LibraryBook extends ModifiableEntity {

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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "libraryBook")
    private List<Serial> serialList = new ArrayList<>();

    @Builder
    public LibraryBook(String image,
                       String title,
                       String author,
                       String publisher,
                       String isbn,
                       String description) {
        this.image = image;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.description = description;
    }
}
