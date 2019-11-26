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
    private LibraryBookId libraryBookId;

    @Embedded
    private SerialRentStatus serialRentStatus;

    public Serial(LibraryBookId libraryBookId, SerialRentStatus serialRentStatus) {
        this.libraryBookId = libraryBookId;
        this.serialRentStatus = serialRentStatus;
    }
}
