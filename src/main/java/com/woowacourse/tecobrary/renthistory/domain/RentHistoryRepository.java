package com.woowacourse.tecobrary.renthistory.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentHistoryRepository extends JpaRepository<RentHistory, Long> {
}
