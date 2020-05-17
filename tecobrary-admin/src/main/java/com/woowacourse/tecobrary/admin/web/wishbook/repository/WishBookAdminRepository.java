package com.woowacourse.tecobrary.admin.web.wishbook.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.woowacourse.tecobrary.domain.wishbook.entity.WishBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static com.woowacourse.tecobrary.domain.wishbook.entity.QWishBook.wishBook;

@Repository
public class WishBookAdminRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public WishBookAdminRepository(JPAQueryFactory jpaQueryFactory) {
        super(WishBook.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Page<WishBook> findAllByCondition(Pageable pageable, WishBookSearchClause searchClause) {
        JPQLQuery<WishBook> jpqlQuery = jpaQueryFactory.selectFrom(wishBook)
                .where(searchClause.where())
                .join(wishBook.user)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(wishBook.id.desc());

        QueryResults<WishBook> queryResults = jpqlQuery.fetchResults();

        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }
}
