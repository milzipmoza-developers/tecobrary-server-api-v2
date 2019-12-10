package com.woowacourse.tecobrary.renthistory.application;

import com.woowacourse.tecobrary.librarybook.application.LibraryBookService;
import com.woowacourse.tecobrary.librarybook.common.LibraryBookStatic;
import com.woowacourse.tecobrary.renthistory.common.RentHistoryStatic;
import com.woowacourse.tecobrary.renthistory.ui.dto.RentRequestDto;
import com.woowacourse.tecobrary.renthistory.ui.dto.RentResponseDto;
import com.woowacourse.tecobrary.serial.application.SerialService;
import com.woowacourse.tecobrary.serial.common.SerialStatic;
import com.woowacourse.tecobrary.serial.exception.NotFoundSerialNumberException;
import com.woowacourse.tecobrary.user.command.application.NotFoundUserException;
import com.woowacourse.tecobrary.user.command.application.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
public class RentReturnServiceTest implements LibraryBookStatic, SerialStatic, RentHistoryStatic {

    @Mock
    private UserService userService;

    @Mock
    private LibraryBookService libraryBookService;

    @Mock
    private SerialService serialService;

    @Mock
    private RentHistoryService rentHistoryService;

    @InjectMocks
    private RentReturnService rentReturnService;

    @DisplayName("존재하지 않는 유저로 대여 시 NotFoundUserException 이 발생한다.")
    @Test
    void failedRentWithNotExistUser() {
        given(userService.existsByUserId(any(Long.class))).willReturn(false);

        assertThrows(NotFoundUserException.class, () -> rentReturnService.rent(TEST_RENT_REQUEST_DTO));
    }

    @DisplayName("존재하지 않는 Serial Number 로 대여 시 NotFoundSerialNumberException 이 발생한다.")
    @Test
    void failedRentWithNotExistSerialNumber() {
        given(userService.existsByUserId(any(Long.class))).willReturn(true);
        given(serialService.existsBySerialNumber(any(Long.class))).willReturn(false);

        assertThrows(NotFoundSerialNumberException.class, () -> rentReturnService.rent(TEST_RENT_REQUEST_DTO));
    }

    @DisplayName("이미 대여 중(rent status = true)인 serial 에 대하여 AlreadyRentBookException 이 발생한다.")
    @Test
    void failedRentWithAlreadyRent() {
        given(userService.existsByUserId(any(Long.class))).willReturn(true);
        given(serialService.existsBySerialNumber(any(Long.class))).willReturn(true);
        given(serialService.checkBySerialNumberIsRent(any(Long.class))).willReturn(true);

        assertThrows(AlreadyRentBookException.class, () -> rentReturnService.rent(TEST_RENT_REQUEST_DTO));
    }

    @DisplayName("rent 메서드가 성공적으로 동작한다.")
    @DirtiesContext
    @Test
    void successfullyRent() {
        given(userService.existsByUserId(any(Long.class))).willReturn(true);
        given(serialService.existsBySerialNumber(any(Long.class))).willReturn(true);
        given(serialService.checkBySerialNumberIsRent(any(Long.class))).willReturn(false);
        given(serialService.findBySerialNumber(any(Long.class))).willReturn(TEST_SERIAL_NOT_RENT);
        given(rentHistoryService.createRentHistory(any(RentRequestDto.class))).willReturn(TEST_RENT_HISTORY_RENT_BOOK);
        given(libraryBookService.findByBookId(any(Long.class))).willReturn(TEST_LIBRARY_BOOK_01);

        RentResponseDto rent = rentReturnService.rent(TEST_RENT_REQUEST_DTO);

        assertThat(rent.getRentInfo().getTitle()).isEqualTo(TEST_LIBRARY_BOOK_TITLE);
        assertThat(rent.getRentInfo().getSerialNumber()).isEqualTo(TEST_SERIAL_NUMBER);
        assertThat(rent.getRentInfo().getStatus()).isEqualTo(true);
        assertThat(rent.getMessage()).isEqualTo("대여에 성공하였습니다.");
    }
}
