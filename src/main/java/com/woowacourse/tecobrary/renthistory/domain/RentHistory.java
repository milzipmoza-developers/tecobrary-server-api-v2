package com.woowacourse.tecobrary.renthistory.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    public RentHistory(RentSerial rentSerial, RentUser rentUser) {
        this.rentSerial = rentSerial;
        this.rentUser = rentUser;
    }
}
