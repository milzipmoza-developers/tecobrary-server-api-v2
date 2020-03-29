package com.woowacourse.tecobrary.renthistory.entity;

import com.woowacourse.tecobrary.common.entity.DeletableEntity;
import com.woowacourse.tecobrary.serial.entity.Serial;
import com.woowacourse.tecobrary.user.entity.User;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
public class RentHistory extends DeletableEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "rent_serial_id")
    private Serial serial;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "rent_user_id")
    private User user;

    public RentHistory(Serial serial, User user) {
        this.serial = serial;
        this.user = user;
    }

    public boolean isSameUser(User user) {
        return this.user.equals(user);
    }

    @Override
    protected LocalDateTime softDelete() {
        return super.softDelete();
    }
}
