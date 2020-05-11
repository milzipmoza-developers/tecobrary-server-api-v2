package com.woowacourse.tecobrary.web.serial.service;

import com.woowacourse.tecobrary.domain.librarybook.entity.LibraryBook;
import com.woowacourse.tecobrary.domain.librarybook.repository.LibraryBookRepository;
import com.woowacourse.tecobrary.domain.serial.entity.Serial;
import com.woowacourse.tecobrary.domain.serial.repository.SerialRepository;
import com.woowacourse.tecobrary.web.serial.dto.SerialInfoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SerialFacadeTest {

    @Autowired
    private SerialRepository serialRepository;

    @Autowired
    private LibraryBookRepository libraryBookRepository;

    @Autowired
    private SerialFacade serialFacade;

    @Test
    void name() {
        LibraryBook libraryBook = libraryBookRepository.save(LibraryBook.builder()
                .title("제목")
                .author("작가")
                .image("이미지")
                .publisher("출판사")
                .isbn("ddd")
                .description("Ddd")
                .build());

        Serial serial = serialRepository.save(Serial.builder()
                .serialNumber(1L)
                .status(false)
                .libraryBook(libraryBookRepository.findById(libraryBook.getId()).orElse(null))
                .build());

        List<SerialInfoDto> serials = serialFacade.findSerialsByBookId(1L);

        assertThat(serials.get(0).getSerialNumber()).isEqualTo(serial.getSerialNumber());
        assertThat(serials.get(0).getStatus()).isEqualTo(serial.isStatus());
    }
}