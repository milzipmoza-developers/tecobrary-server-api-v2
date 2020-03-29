package com.woowacourse.tecobrary.renthistory.repository;

import com.woowacourse.tecobrary.renthistory.entity.RentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentHistoryRepository extends JpaRepository<RentHistory, Long> {
}
