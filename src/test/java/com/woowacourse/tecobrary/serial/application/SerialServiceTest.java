package com.woowacourse.tecobrary.serial.application;

import com.woowacourse.tecobrary.serial.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Collections;

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

    @BeforeEach
    void setUp() {
        serial = new Serial(new SerialInfo(1L), new SerialLibraryBook(1L), new SerialRentStatus(false));
    }

    @DisplayName("save 메서드가 성공적으로 동작한다.")
    @Test
    void successfullySave() {
        given(serialRepository.save(any(Serial.class))).willReturn(serial);

        Serial savedSerial = serialService.save(serial);
        ReflectionTestUtils.setField(serial, "id", 1L);

        assertThat(savedSerial.getId()).isEqualTo(1L);
        assertThat(savedSerial.getSerialInfo()).isEqualTo(1L);
        assertThat(savedSerial.getStatus()).isEqualTo(false);
        assertThat(savedSerial.getBookId()).isEqualTo(1L);
    }

    @DisplayName("existsBySerialNumber 메서드가 성공적으로 동작한다.")
    @Test
    void successfullyExistsBySerialNumber() {
        given(serialRepository.existsBySerialInfoSerialNumber(1L)).willReturn(true);

        assertThat(serialService.existsBySerialNumber(1L)).isTrue();
    }

    @DisplayName("findSerialsByBookId 메서드가 성공적으로 동작한다.")
    @Test
    void successfullyFindSerialsByBookId() {
        given(serialRepository.findAllBySerialLibraryBookBookId(1L)).willReturn(Collections.emptyList());

        assertThat(serialService.findSerialsByBookId(1L)).isNotNull();
    }
}