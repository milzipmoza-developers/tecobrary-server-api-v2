package com.woowacourse.tecobrary.domain.common.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class ModifiableBaseEntity extends ModifiableEntity {

    @CreatedBy
    @Column(length = 50)
    private String createdBy;

    @LastModifiedBy
    @Column(length = 50)
    private String modifiedBy;
}
