package com.woowacourse.tecobrary.serial.application;

import com.woowacourse.tecobrary.serial.domain.*;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
class SerialServiceTest {

    @Mock
    private SerialRepository serialRepository;

    @InjectMocks
    private SerialService serialService;

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
        given(serialRepository.save(any(Serial.class))).willReturn(serial);

        SerialCreateResponseDto serialCreateResponseDto = serialService.save(serialCreateRequestDto);

        assertThat(serialCreateResponseDto.getMessage()).isEqualTo("등록에 성공하였습니다.");
        assertThat(serialCreateResponseDto.getSerial().getSerialNumber()).isEqualTo(1L);
        assertThat(serialCreateResponseDto.getSerial().getBookId()).isEqualTo(1L);
        assertThat(serialCreateResponseDto.getSerial().getStatus()).isEqualTo(false);
    }
}