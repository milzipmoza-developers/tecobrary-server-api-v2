package com.woowacourse.tecobrary.serial.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "Serials")
@Access(AccessType.FIELD)
public class Serial {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Embedded
    private SerialLibraryBook serialLibraryBook;

    @Embedded
    private SerialRentStatus serialRentStatus;

    public Serial(SerialLibraryBook serialLibraryBook, SerialRentStatus serialRentStatus) {
        this.serialLibraryBook = serialLibraryBook;
        this.serialRentStatus = serialRentStatus;
    }
}
