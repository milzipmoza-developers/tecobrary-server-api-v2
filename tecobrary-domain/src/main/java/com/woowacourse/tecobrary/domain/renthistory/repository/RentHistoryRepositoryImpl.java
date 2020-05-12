package com.woowacourse.tecobrary.domain.renthistory.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import com.woowacourse.tecobrary.domain.renthistory.entity.RentHistory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.woowacourse.tecobrary.domain.librarybook.entity.QLibraryBook.libraryBook;
import static com.woowacourse.tecobrary.domain.renthistory.entity.QRentHistory.rentHistory;
import static com.woowacourse.tecobrary.domain.serial.entity.QSerial.serial;

public class RentHistoryRepositoryImpl extends QuerydslRepositorySupport implements RentHistoryRepositoryCustom {

    public RentHistoryRepositoryImpl() {
        super(RentHistory.class);
    }

    @Override
    public List<RentHistory> findAllByUserIdAndDeletedAtIsNullWithSerialAndLibraryBook(Long userId) {
        JPQLQuery<RentHistory> query = from(rentHistory)
                .where(rentHistory.user.id.eq(userId))
                .where(rentHistory.deletedAt.isNull())
                .join(rentHistory.serial, serial)
                .fetchJoin()
                .join(rentHistory.serial.libraryBook, libraryBook)
                .fetchJoin()
                .orderBy(rentHistory.createdAt.desc());

        QueryResults<RentHistory> results = query.fetchResults();

        return results.getResults();
    }

    @Override
    public List<RentHistory> findAllByUserIdAndDeletedAtIsNotNullWithSerialAndLibraryBook(Long userId) {
        JPQLQuery<RentHistory> query = from(rentHistory)
                .where(rentHistory.user.id.eq(userId))
                .where(rentHistory.deletedAt.isNotNull())
                .join(rentHistory.serial, serial)
                .fetchJoin()
                .join(rentHistory.serial.libraryBook, libraryBook)
                .fetchJoin()
                .orderBy(rentHistory.createdAt.asc());

        QueryResults<RentHistory> results = query.fetchResults();

        return results.getResults();
    }
}
