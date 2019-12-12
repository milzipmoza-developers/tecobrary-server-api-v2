package com.woowacourse.tecobrary.renthistory.application;

import com.woowacourse.tecobrary.librarybook.application.LibraryBookService;
import com.woowacourse.tecobrary.librarybook.common.LibraryBookStatic;
import com.woowacourse.tecobrary.renthistory.common.RentHistoryStatic;
import com.woowacourse.tecobrary.renthistory.ui.dto.RentHistoryDto;
import com.woowacourse.tecobrary.serial.application.SerialService;
import com.woowacourse.tecobrary.serial.common.SerialStatic;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class RentHistoryReadServiceTest implements LibraryBookStatic, SerialStatic, RentHistoryStatic {

    @Mock
    private RentHistoryService rentHistoryService;

    @Mock
    private LibraryBookService libraryBookService;

    @Mock
    private SerialService serialService;

    @InjectMocks
    private RentHistoryReadService rentHistoryReadService;

    @DisplayName("회원의 도서대여 목록을 조회한다.")
    @Test
    void findAllByUserId() {
        given(rentHistoryService.findAllByRentUser(TEST_RENT_HISTORY_USER_ID_16))
                .willReturn(Arrays.asList(TEST_RENT_HISTORY, TEST_RENT_HISTORY_02));
        given(serialService.findByRentSerial(TEST_RENT_SERIAL)).willReturn(TEST_SERIAL_NOT_RENT);
        given(serialService.findByRentSerial(TEST_RENT_SERIAL_02)).willReturn(TEST_SERIAL_NOT_RENT_02);
        given(libraryBookService.findByBookId(TEST_BOOK_ID)).willReturn(TEST_LIBRARY_BOOK_19);
        given(libraryBookService.findByBookId(TEST_BOOK_ID_02)).willReturn(TEST_LIBRARY_BOOK_07);

        List<RentHistoryDto> rentHistoryDtos = rentHistoryReadService.findAllByUserId(TEST_RENT_HISTORY_USER_ID_16);

        RentHistoryDto rentHistoryDto1 = rentHistoryDtos.get(0);
        assertThat(rentHistoryDto1.getSerial()).isEqualTo(TEST_SERIAL_NUMBER);
        assertThat(rentHistoryDto1.getTitle()).isEqualTo(TEST_LIBRARY_BOOK_TITLE_19);
        assertThat(rentHistoryDto1.getUserId()).isEqualTo(TEST_RENT_HISTORY_USER_ID_16);

        RentHistoryDto rentHistoryDto2 = rentHistoryDtos.get(1);
        assertThat(rentHistoryDto2.getSerial()).isEqualTo(TEST_SERIAL_NUMBER_02);
        assertThat(rentHistoryDto2.getTitle()).isEqualTo(TEST_LIBRARY_BOOK_TITLE_07);
        assertThat(rentHistoryDto2.getUserId()).isEqualTo(TEST_RENT_HISTORY_USER_ID_16);
    }
}