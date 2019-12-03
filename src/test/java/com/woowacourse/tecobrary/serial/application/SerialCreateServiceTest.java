package com.woowacourse.tecobrary.serial.application;

import com.woowacourse.tecobrary.librarybook.application.LibraryBookService;
import com.woowacourse.tecobrary.serial.domain.Serial;
import com.woowacourse.tecobrary.serial.domain.SerialLibraryBook;
import com.woowacourse.tecobrary.serial.domain.SerialNumber;
import com.woowacourse.tecobrary.serial.domain.SerialRentStatus;
import com.woowacourse.tecobrary.serial.exception.NotFoundSerialTargetException;
import com.woowacourse.tecobrary.serial.ui.dto.SerialCreateRequestDto;
import com.woowacourse.tecobrary.serial.ui.dto.SerialCreateResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class SerialCreateServiceTest {

    @Mock
    private SerialService serialService;

    @Mock
    private LibraryBookService libraryBookService;

    @InjectMocks
    private SerialCreateService serialCreateService;

    private Serial serial;
    private SerialCreateRequestDto serialCreateRequestDto;

    @BeforeEach
    void setUp() {
        serial = new Serial(new SerialNumber(1L), new SerialLibraryBook(1L), new SerialRentStatus(false));
        serialCreateRequestDto = new SerialCreateRequestDto(1L, 1L);
    }

    @DisplayName("save 가 정상적으로 동작한다.")
    @Test
    void saveSuccess() {
        given(serialService.save(any(Serial.class))).willReturn(serial);
        given(libraryBookService.existsById(1L)).willReturn(true);

        SerialCreateResponseDto serialCreateResponseDto = serialCreateService.save(serialCreateRequestDto);

        assertThat(serialCreateResponseDto.getMessage()).isEqualTo("등록에 성공하였습니다.");
        assertThat(serialCreateResponseDto.getSerial().getSerialNumber()).isEqualTo(1L);
        assertThat(serialCreateResponseDto.getSerial().getBookId()).isEqualTo(1L);
        assertThat(serialCreateResponseDto.getSerial().getStatus()).isEqualTo(false);
    }

    @DisplayName("해당하는 책이 없을 경우 실패한다.")
    @Test
    void saveFailed_NotFoundSerialTarget() {
        given(serialService.save(any(Serial.class))).willReturn(serial);
        given(libraryBookService.existsById(1L)).willReturn(false);

        assertThrows(NotFoundSerialTargetException.class, () -> serialCreateService.save(serialCreateRequestDto));
    }
}