package com.woowacourse.tecobrary.serial.application;

import com.woowacourse.tecobrary.serial.domain.Serial;
import com.woowacourse.tecobrary.serial.domain.SerialRepository;
import com.woowacourse.tecobrary.serial.exception.NotFoundSerialNumberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public boolean existsBySerialNumber(final Long serialNumber) {
        return serialRepository.existsBySerialInfoSerialNumber(serialNumber);
    }

    public List<Serial> findSerialsByBookId(final Long bookId) {
        return serialRepository.findAllBySerialLibraryBookBookId(bookId);
    }

    @Transactional
    public boolean deleteBySerialNumber(final Long serialNumber) {
        if (serialRepository.existsBySerialInfoSerialNumber(serialNumber)) {
            serialRepository.deleteBySerialInfoSerialNumber(serialNumber);
            return true;
        }
        throw new NotFoundSerialNumberException();
    }
}
