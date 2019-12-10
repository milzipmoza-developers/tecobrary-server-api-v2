package com.woowacourse.tecobrary.serial.domain;

import com.woowacourse.tecobrary.serial.exception.AlreadyRentStatusException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class SerialRentStatus {

    @Column(name = "status",
            columnDefinition = "TINYINT(1)")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean status;

    public SerialRentStatus(boolean status) {
        this.status = status;
    }

    public boolean toRent() {
        checkRent();
        this.status = true;
        return this.status;
    }

    private void checkRent() {
        if (this.status) {
            throw new AlreadyRentStatusException();
        }
    }
}
