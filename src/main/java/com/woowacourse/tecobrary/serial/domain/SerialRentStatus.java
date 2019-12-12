package com.woowacourse.tecobrary.serial.domain;

import com.woowacourse.tecobrary.serial.exception.AlreadyRentStatusException;
import com.woowacourse.tecobrary.serial.exception.AlreadyReturnStatusException;
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
        return setRent();
    }

    private void checkRent() {
        if (this.status) {
            throw new AlreadyRentStatusException();
        }
    }

    private boolean setRent() {
        return this.status = true;
    }

    public boolean toReturn() {
        checkReturned();
        return setReturned();
    }

    private void checkReturned() {
        if (!this.status) {
            throw new AlreadyReturnStatusException();
        }
    }

    private boolean setReturned() {
        return this.status = false;
    }
}
