package com.woowacourse.tecobrary.serial.application;

import com.woowacourse.tecobrary.serial.domain.Serial;
import com.woowacourse.tecobrary.serial.domain.SerialRepository;
import com.woowacourse.tecobrary.serial.ui.dto.SerialCreateRequestDto;
import com.woowacourse.tecobrary.serial.ui.dto.SerialCreateResponseDto;
import com.woowacourse.tecobrary.serial.util.SerialMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SerialService {

    private final SerialRepository serialRepository;

    @Autowired
    public SerialService(final SerialRepository serialRepository) {
        this.serialRepository = serialRepository;
    }

    public SerialCreateResponseDto save(final SerialCreateRequestDto serialCreateRequestDto) {
        Serial serial = SerialMapper.toEntity(serialCreateRequestDto);
        Serial savedSerial = serialRepository.save(serial);
        return SerialMapper.toDto(savedSerial, "등록에 성공하였습니다.");
    }
}
