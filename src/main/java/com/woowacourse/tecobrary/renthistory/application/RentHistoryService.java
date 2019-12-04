package com.woowacourse.tecobrary.renthistory.application;

import com.woowacourse.tecobrary.renthistory.domain.RentHistory;
import com.woowacourse.tecobrary.renthistory.domain.RentHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentHistoryService {

    private final RentHistoryRepository rentHistoryRepository;

    @Autowired
    public RentHistoryService(final RentHistoryRepository rentHistoryRepository) {
        this.rentHistoryRepository = rentHistoryRepository;
    }

    public List<RentHistory> findAllByRentUser(final Long userId) {
        return rentHistoryRepository.findAllByRentUserUserIdAndDeletedAtIsNull(userId);
    }
}
