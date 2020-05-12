package com.woowacourse.tecobrary.domain.renthistory.entity;

import com.woowacourse.tecobrary.domain.common.entity.DeletableEntity;
import com.woowacourse.tecobrary.domain.serial.entity.Serial;
import com.woowacourse.tecobrary.domain.user.entity.User;
import lombok.*;

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

    @Builder
    public RentHistory(Serial serial, User user) {
        this.serial = serial;
        this.user = user;
    }

    public boolean isSameUser(User user) {
        return this.user.equals(user);
    }

    @Override
    public LocalDateTime softDelete() {
        return super.softDelete();
    }
}
