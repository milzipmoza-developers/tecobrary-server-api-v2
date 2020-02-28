package com.woowacourse.tecobrary.common.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
public class DeletableEntity extends ModifiableEntity {

    @Column(name = "deletedAt",
            columnDefinition = "DATETIME default NULL")
    private LocalDateTime deletedAt;

    protected LocalDateTime softDelete() {
        checkDeletable();
        this.deletedAt = LocalDateTime.now();
        return this.deletedAt;
    }

    private void checkDeletable() {
        if (deletedAt != null) {
            throw new NotDeletableException();
        }
    }
}
