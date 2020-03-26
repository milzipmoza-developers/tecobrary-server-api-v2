package com.woowacourse.tecobrary.librarybook.entity;

import com.woowacourse.tecobrary.common.entity.DefaultBook;
import com.woowacourse.tecobrary.common.entity.ModifiableEntity;
import com.woowacourse.tecobrary.common.domain.BookCoverUrl;
import com.woowacourse.tecobrary.common.domain.BookInfo;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "LibraryBooks")
@Access(AccessType.FIELD)
public class LibraryBook extends ModifiableEntity implements DefaultBook {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Embedded
    private BookCoverUrl libraryBookCoverUrl;

    @Embedded
    private BookInfo libraryBookInfo;

    public LibraryBook(final BookCoverUrl libraryBookCoverUrl, final BookInfo libraryBookInfo) {
        this.libraryBookCoverUrl = libraryBookCoverUrl;
        this.libraryBookInfo = libraryBookInfo;
    }

    public String getTitle() {
        return libraryBookInfo.getTitle();
    }

    public String getAuthor() {
        return libraryBookInfo.getAuthor();
    }

    public String getPublisher() {
        return libraryBookInfo.getPublisher();
    }

    public String getIsbn() {
        return libraryBookInfo.getIsbn();
    }

    public String getDescription() {
        return libraryBookInfo.getDescription();
    }

    public String getImage() {
        return libraryBookCoverUrl.getImage();
    }
}
