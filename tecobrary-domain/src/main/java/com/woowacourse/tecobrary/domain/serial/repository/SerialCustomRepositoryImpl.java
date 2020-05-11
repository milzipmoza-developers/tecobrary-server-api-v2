package com.woowacourse.tecobrary.domain.serial.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import com.woowacourse.tecobrary.domain.serial.entity.Serial;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Optional;

import static com.woowacourse.tecobrary.domain.librarybook.entity.QLibraryBook.libraryBook;
import static com.woowacourse.tecobrary.domain.serial.entity.QSerial.serial;

public class SerialCustomRepositoryImpl extends QuerydslRepositorySupport implements SerialCustomRepository {

    public SerialCustomRepositoryImpl() {
        super(Serial.class);
    }

    @Override
    public List<Serial> findAllSerialByLibraryBookId(Long id) {
        JPQLQuery<Serial> query = from(serial)
                .where(serial.libraryBook.id.eq(id))
                .join(serial.libraryBook)
                .fetchJoin()
                .orderBy(serial.serialNumber.desc());

        QueryResults<Serial> result = query.fetchResults();

        return result.getResults();
    }

    @Override
    public Optional<Serial> findBySerialNumberWithLibraryBook(Long serialNumber) {
        return Optional.ofNullable(from(serial)
                .where(serial.serialNumber.eq(serialNumber))
                .join(serial.libraryBook, libraryBook)
                .fetchJoin()
                .fetchOne()
        );
    }
}
