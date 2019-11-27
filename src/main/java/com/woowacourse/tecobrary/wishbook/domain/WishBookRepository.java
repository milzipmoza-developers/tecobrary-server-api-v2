package com.woowacourse.tecobrary.wishbook.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishBookRepository extends JpaRepository<WishBook, Long> {
}
