package com.woowacourse.tecobrary.domain.wishbook.repository;

import com.woowacourse.tecobrary.domain.wishbook.entity.WishBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishBookRepository extends JpaRepository<WishBook, Long> {

    boolean existsByIsbn(String isbn);
}
