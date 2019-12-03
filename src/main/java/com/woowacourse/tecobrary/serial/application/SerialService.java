package com.woowacourse.tecobrary.serial.application;

import com.woowacourse.tecobrary.serial.domain.Serial;
import com.woowacourse.tecobrary.serial.domain.SerialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SerialService {

    private final SerialRepository serialRepository;

    @Autowired
    public SerialService(final SerialRepository serialRepository) {
        this.serialRepository = serialRepository;
    }

    public Serial save(final Serial serial) {
        return serialRepository.save(serial);
    }
}
