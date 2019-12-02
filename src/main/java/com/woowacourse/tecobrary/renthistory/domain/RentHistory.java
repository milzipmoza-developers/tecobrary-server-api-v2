package com.woowacourse.tecobrary.renthistory.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@EqualsAndHashCode(of = "rentNo")
@Table(name = "RentHistories")
@Access(AccessType.FIELD)
public class RentHistory {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long rentNo;

    @Embedded
    private RentSerial rentSerial;

    @Embedded
    private RentUser rentUser;

    @CreationTimestamp
    @Column(name = "createdAt",
            updatable = false,
            nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt",
            nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "deletedAt",
            columnDefinition = "DATETIME default NULL")
    private LocalDateTime deletedAt;

    public RentHistory(RentSerial rentSerial, RentUser rentUser) {
        this.rentSerial = rentSerial;
        this.rentUser = rentUser;
    }
}
