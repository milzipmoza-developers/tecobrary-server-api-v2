package com.woowacourse.tecobrary.serial.domain;

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
@EqualsAndHashCode(of = "id")
@Table(name = "Serials")
@Access(AccessType.FIELD)
public class Serial {

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

    @CreationTimestamp
    @Column(name = "createdAt",
            updatable = false,
            nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updatedAt",
            nullable = false)
    private LocalDateTime updatedAt;

    public Serial(SerialInfo serialInfo, SerialLibraryBook serialLibraryBook, SerialRentStatus serialRentStatus) {
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
