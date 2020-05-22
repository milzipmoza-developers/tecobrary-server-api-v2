package com.woowacourse.tecobrary.admin.web.admin.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.woowacourse.tecobrary.domain.admin.entity.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import static com.woowacourse.tecobrary.domain.admin.entity.QAdmin.admin;

@Repository
public class AdminManageRepository extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    public AdminManageRepository(JPAQueryFactory jpaQueryFactory) {
        super(Admin.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public Page<Admin> searchAdmin(Pageable pageable, AdminSearchClause clause) {
        JPQLQuery<Admin> jpqlQuery = jpaQueryFactory.selectFrom(admin)
                .where(clause.where())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(admin.id.asc());

        QueryResults<Admin> queryResults = jpqlQuery.fetchResults();

        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }
}
