package com.woowacourse.tecobrary.domain.wishbook.repository;

import com.woowacourse.tecobrary.domain.wishbook.entity.WishBookHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishBookHistoryRepository extends JpaRepository<WishBookHistory, Long> {
}
