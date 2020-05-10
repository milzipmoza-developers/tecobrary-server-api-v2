package com.woowacourse.tecobrary.domain.renthistory.repository;

import com.woowacourse.tecobrary.domain.renthistory.entity.RentHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentHistoryRepository extends JpaRepository<RentHistory, Long> {

    List<RentHistory> findAllByUserIdAndDeletedAtIsNull(Long userId);

    List<RentHistory> findAllByUserIdAndDeletedAtIsNotNull(Long userId);

    Optional<RentHistory> findBySerialSerialNumberAndUserIdAndDeletedAtIsNull(Long serialNumber, Long userId);
}
