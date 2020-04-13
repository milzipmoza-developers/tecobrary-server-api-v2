package com.woowacourse.tecobrary.admin.web.wishbook.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.woowacourse.tecobrary.domain.wishbook.entity.WishBookStatus;
import lombok.Builder;

import java.util.*;

import static com.woowacourse.tecobrary.domain.wishbook.entity.QWishBook.wishBook;

public class WishBookSearchClause {

    private String requestUser;
    private WishBookStatus status;

    @Builder
    public WishBookSearchClause(String requestUser, WishBookStatus status) {
        this.requestUser = requestUser;
        this.status = status;
    }

    Predicate[] where(Predicate... predicates) {
        List<Predicate> result = new ArrayList<>(Arrays.asList(predicates));
        result.addAll(Arrays.asList(
                getRequestUserExpression(),
                getStatusExpression()
        ));
        return result.stream()
                .filter(Objects::nonNull)
                .toArray(Predicate[]::new);
    }

    private BooleanExpression getRequestUserExpression() {
        return Optional.ofNullable(requestUser)
                .map(wishBook.user.name::eq)
                .orElse(null);
    }

    private BooleanExpression getStatusExpression() {
        return Optional.ofNullable(status)
                .map(wishBook.status::eq)
                .orElse(null);
    }
}
