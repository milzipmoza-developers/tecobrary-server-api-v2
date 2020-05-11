package com.woowacourse.tecobrary.web.serial.service;

import com.woowacourse.tecobrary.domain.serial.entity.Serial;
import com.woowacourse.tecobrary.domain.serial.repository.SerialRepository;
import com.woowacourse.tecobrary.web.serial.converter.SerialConverter;
import com.woowacourse.tecobrary.web.serial.dto.SerialBookInfoDto;
import com.woowacourse.tecobrary.web.serial.dto.SerialInfoDto;
import com.woowacourse.tecobrary.web.serial.exception.SerialNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SerialFacade {

    private final SerialRepository serialRepository;
    private final SerialConverter serialConverter;

    public List<SerialInfoDto> findSerialsByBookId(Long bookId) {
        return serialRepository.findAllSerialByLibraryBookId(bookId)
                .stream()
                .map(serialConverter::convertInfoDto)
                .collect(Collectors.toList());
    }

    public SerialBookInfoDto findBookBySerialNumber(Long serialNumber) {
        Serial serial = serialRepository.findBySerialNumberWithLibraryBook(serialNumber)
                .orElseThrow(SerialNotFoundException::new);

        return serialConverter.convertBookInfoDto(serial.getLibraryBook());
    }
}
