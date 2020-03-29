package com.woowacourse.tecobrary.wishbook.repository;

import com.woowacourse.tecobrary.wishbook.entity.WishBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishBookRepository extends JpaRepository<WishBook, Long> {
}
