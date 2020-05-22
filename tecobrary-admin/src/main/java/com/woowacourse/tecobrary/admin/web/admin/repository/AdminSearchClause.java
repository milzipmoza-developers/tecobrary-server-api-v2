package com.woowacourse.tecobrary.admin.web.admin.repository;


import com.nimbusds.oauth2.sdk.util.StringUtils;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.Builder;

import java.util.*;

import static com.woowacourse.tecobrary.domain.admin.entity.QAdmin.admin;

public class AdminSearchClause {

    private String name;
    private String email;

    @Builder
    public AdminSearchClause(String name, String email) {
        this.name = name;
        this.email = email;
    }

    Predicate[] where(Predicate... predicates) {
        List<Predicate> result = new ArrayList<>(Arrays.asList(predicates));
        result.addAll(Arrays.asList(
                getNameExpression(),
                getEmailExpression()
        ));
        return result.stream()
                .filter(Objects::nonNull)
                .toArray(Predicate[]::new);
    }

    private BooleanExpression getNameExpression() {
        return Optional.ofNullable(name)
                .filter(StringUtils::isNotBlank)
                .map(admin.name::like)
                .orElse(null);
    }

    private BooleanExpression getEmailExpression() {
        return Optional.ofNullable(email)
                .filter(StringUtils::isNotBlank)
                .map(admin.email::like)
                .orElse(null);
    }

}
