package com.woowacourse.tecobrary.renthistory.domain;

import com.woowacourse.tecobrary.common.entity.DeletableEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@EqualsAndHashCode(of = "id", callSuper = false)
@Table(name = "RentHistories")
@Access(AccessType.FIELD)
public class RentHistory extends DeletableEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;

    @Embedded
    private RentSerial rentSerial;

    @Embedded
    private RentUser rentUser;

    @Builder
    public RentHistory(final RentSerial rentSerial, final RentUser rentUser) {
        this.rentSerial = rentSerial;
        this.rentUser = rentUser;
    }

    @Override
    protected LocalDateTime softDelete() {
        return super.softDelete();
    }

    public Long getSerialNumber() {
        return rentSerial.getSerialId();
    }

    public Long getUserId() {
        return rentUser.getUserId();
    }

    public boolean isDifferentUser(final Long userId) {
        return !rentUser.isSameUser(userId);
    }
}
