package com.woowacourse.tecobrary.admin.web.librarybook.repository;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.Builder;

import java.util.*;

import static com.woowacourse.tecobrary.domain.librarybook.entity.QLibraryBook.libraryBook;

public class LibraryBookSearchClause {
    private String title;
    private String author;
    private String publisher;
    private String isbn;

    @Builder
    public LibraryBookSearchClause(String title, String author, String publisher, String isbn) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
    }

    Predicate[] where(Predicate... predicates) {
        List<Predicate> result = new ArrayList<>(Arrays.asList(predicates));
        result.addAll(Arrays.asList(
                getTitleExpression(),
                getAuthorExpression(),
                getPublisherExpression(),
                getIsbnExpression()
        ));
        return result.stream()
                .filter(Objects::nonNull)
                .toArray(Predicate[]::new);
    }

    private BooleanExpression getTitleExpression() {
        return Optional.ofNullable(title)
                .map(libraryBook.title::like)
                .orElse(null);
    }

    private BooleanExpression getAuthorExpression() {
        return Optional.ofNullable(author)
                .map(libraryBook.author::like)
                .orElse(null);
    }

    private BooleanExpression getPublisherExpression() {
        return Optional.ofNullable(publisher)
                .map(libraryBook.publisher::like)
                .orElse(null);
    }

    private BooleanExpression getIsbnExpression() {
        return Optional.ofNullable(isbn)
                .map(libraryBook.isbn::like)
                .orElse(null);
    }
}
