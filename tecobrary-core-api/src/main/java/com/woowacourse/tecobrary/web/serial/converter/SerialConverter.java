package com.woowacourse.tecobrary.web.serial.converter;

import com.woowacourse.tecobrary.domain.librarybook.entity.LibraryBook;
import com.woowacourse.tecobrary.domain.serial.entity.Serial;
import com.woowacourse.tecobrary.web.serial.dto.SerialBookInfoDto;
import com.woowacourse.tecobrary.web.serial.dto.SerialInfoDto;
import org.springframework.stereotype.Component;

@Component
public class SerialConverter {

    public SerialInfoDto convertInfoDto(Serial serial) {
        return SerialInfoDto.builder()
                .serialNumber(serial.getSerialNumber())
                .status(serial.isStatus())
                .build();
    }

    public SerialBookInfoDto convertBookInfoDto(LibraryBook libraryBook) {
        return SerialBookInfoDto.builder()
                .id(libraryBook.getId())
                .title(libraryBook.getTitle())
                .image(libraryBook.getImage())
                .author(libraryBook.getAuthor())
                .publisher(libraryBook.getPublisher())
                .isbn(libraryBook.getIsbn())
                .build();
    }
}
