package com.woowacourse.tecobrary.serial.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SerialRepository extends JpaRepository<Serial, Long> {

    Optional<Serial> findBySerialInfoSerialNumber(Long serialNumber);

    boolean existsBySerialInfoSerialNumber(Long serialNumber);

    List<Serial> findAllBySerialLibraryBookBookId(Long bookId);

    void deleteBySerialInfoSerialNumber(Long serialNumber);

    boolean existsBySerialInfoSerialNumberAndSerialRentStatusIsTrue(Long id);
}
