package com.woowacourse.tecobrary.renthistory.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentHistoryRepository extends JpaRepository<RentHistory, Long> {

    List<RentHistory> findAllByRentUserUserIdAndDeletedAtIsNull(Long userId);

    Optional<RentHistory> findByRentSerialSerialIdAndDeletedAtIsNull(Long serial);

    List<RentHistory> findAllByRentUserUserIdAndDeletedAtIsNotNull(Long userId);
}
