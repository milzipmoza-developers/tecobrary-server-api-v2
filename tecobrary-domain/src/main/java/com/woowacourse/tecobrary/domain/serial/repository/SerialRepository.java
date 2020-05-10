package com.woowacourse.tecobrary.domain.serial.repository;

import com.woowacourse.tecobrary.domain.serial.entity.Serial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SerialRepository extends JpaRepository<Serial, Long> {

    List<Serial> findAllByLibraryBookId(Long id);

    Optional<Serial> findBySerialNumber(Long serialNumber);

    boolean existsBySerialNumber(Long serialNumber);
}
