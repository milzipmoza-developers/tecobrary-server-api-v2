package com.woowacourse.tecobrary.domain.renthistory.repository;

import com.woowacourse.tecobrary.domain.renthistory.entity.RentHistory;

import java.util.List;

public interface RentHistoryRepositoryCustom {

    List<RentHistory> findAllByUserIdAndDeletedAtIsNullWithSerialAndLibraryBook(Long userId);

    List<RentHistory> findAllByUserIdAndDeletedAtIsNotNullWithSerialAndLibraryBook(Long userId);
}
