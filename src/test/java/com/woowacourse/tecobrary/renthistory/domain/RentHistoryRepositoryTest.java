package com.woowacourse.tecobrary.renthistory.domain;

import com.woowacourse.tecobrary.renthistory.common.RentHistoryStatic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RentHistoryRepositoryTest implements RentHistoryStatic {

    @Autowired
    private RentHistoryRepository rentHistoryRepository;

    @DisplayName("회원의 대여목록을 반환한다.")
    @Test
    void findAllByRentUserUserIdAndDeletedAtIsNull() {
        ReflectionTestUtils.setField(TEST_RENT_HISTORY, "id", TEST_RENT_HISTORY_ID);
        ReflectionTestUtils.setField(TEST_RENT_HISTORY_02, "id", TEST_RENT_HISTORY_ID_02);
        List<RentHistory> rentHistories = rentHistoryRepository.findAllByRentUserUserIdAndDeletedAtIsNull(16L);
        assertThat(rentHistories.get(0)).isEqualTo(TEST_RENT_HISTORY);
        assertThat(rentHistories.get(1)).isEqualTo(TEST_RENT_HISTORY_02);
    }
}