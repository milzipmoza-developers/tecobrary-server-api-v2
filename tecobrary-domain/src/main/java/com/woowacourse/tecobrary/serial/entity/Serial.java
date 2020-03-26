package com.woowacourse.tecobrary.serial.entity;

import com.woowacourse.tecobrary.common.entity.ModifiableEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "Serials")
@Access(AccessType.FIELD)
public class Serial extends ModifiableEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Embedded
    private SerialInfo serialInfo;

    @Embedded
    private SerialLibraryBook serialLibraryBook;

    @Embedded
    private SerialRentStatus serialRentStatus;

    public Serial(final SerialInfo serialInfo, final SerialLibraryBook serialLibraryBook, final SerialRentStatus serialRentStatus) {
        this.serialInfo = serialInfo;
        this.serialLibraryBook = serialLibraryBook;
        this.serialRentStatus = serialRentStatus;
    }

    public Long getSerialNumber() {
        return serialInfo.getSerialNumber();
    }

    public Long getBookId() {
        return serialLibraryBook.getBookId();
    }

    public boolean getStatus() {
        return serialRentStatus.isStatus();
    }

    public boolean updateStatusToRent() {
        return serialRentStatus.toRent();
    }

    public boolean updateStatusToReturn() {
        return serialRentStatus.toReturn();
    }
}
