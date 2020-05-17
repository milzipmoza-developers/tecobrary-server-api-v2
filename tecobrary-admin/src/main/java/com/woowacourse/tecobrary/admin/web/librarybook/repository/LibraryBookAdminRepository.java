package com.woowacourse.tecobrary.admin.web.librarybook.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.woowacourse.tecobrary.domain.librarybook.entity.LibraryBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static com.woowacourse.tecobrary.domain.librarybook.entity.QLibraryBook.libraryBook;

@Repository
public class LibraryBookAdminRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public LibraryBookAdminRepository(JPAQueryFactory jpaQueryFactory) {
        super(LibraryBook.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Page<LibraryBook> findAllByCondition(Pageable pageable, LibraryBookSearchClause searchClause) {
        JPQLQuery<LibraryBook> jpqlQuery = jpaQueryFactory.selectFrom(libraryBook)
                .where(searchClause.where())
                .leftJoin(libraryBook.serialList)
                .fetchJoin()
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(libraryBook.id.asc());

        QueryResults<LibraryBook> queryResults = jpqlQuery.fetchResults();

        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }
}
