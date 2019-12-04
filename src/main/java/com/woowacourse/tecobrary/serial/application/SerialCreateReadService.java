package com.woowacourse.tecobrary.serial.application;

import com.woowacourse.tecobrary.librarybook.application.LibraryBookService;
import com.woowacourse.tecobrary.serial.domain.Serial;
import com.woowacourse.tecobrary.serial.exception.NotFoundSerialTargetException;
import com.woowacourse.tecobrary.serial.exception.UniqueConstraintException;
import com.woowacourse.tecobrary.serial.ui.dto.SerialCreateRequestDto;
import com.woowacourse.tecobrary.serial.ui.dto.SerialCreateResponseDto;
import com.woowacourse.tecobrary.serial.ui.dto.SerialsResponseDto;
import com.woowacourse.tecobrary.serial.util.SerialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SerialCreateReadService {

    private final SerialService serialService;
    private final LibraryBookService libraryBookService;

    @Autowired
    public SerialCreateReadService(final SerialService serialService, final LibraryBookService libraryBookService) {
        this.serialService = serialService;
        this.libraryBookService = libraryBookService;
    }

    public SerialCreateResponseDto save(final SerialCreateRequestDto serialCreateRequestDto) {
        checkExistsLibraryBookId(serialCreateRequestDto.getBookId());
        checkNotExistsSerialNumber(serialCreateRequestDto.getSerialNumber());

        Serial serial = SerialMapper.toEntity(serialCreateRequestDto);
        Serial savedSerial = serialService.save(serial);
        return SerialMapper.toDto(savedSerial, "등록에 성공하였습니다.");
    }

    private void checkExistsLibraryBookId(Long bookId) {
        if (!libraryBookService.existsById(bookId)) {
            throw new NotFoundSerialTargetException();
        }
    }

    private void checkNotExistsSerialNumber(Long serialNumber) {
        if (serialService.existsBySerialNumber(serialNumber)) {
            throw new UniqueConstraintException();
        }
    }

    public List<SerialsResponseDto> findSerialsByBookId(final Long bookId) {
        checkExistsLibraryBookId(bookId);

        return serialService.findSerialsByBookId(bookId)
                .stream()
                .map(SerialMapper::toDto)
                .collect(Collectors.toList());
    }
}
