package com.admin.core.domain;

import com.admin.common.code.UserStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "USER_STATUS_HISTORY")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserStatusHistoryEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "user_status")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Column(name = "active")
    private Boolean active = true;

    @Embedded
    private PeriodDate period = PeriodDate.start();

    public void end() {
        this.period.end();
        this.active = false;
    }

    public static UserStatusHistoryEntity toEntity(UserEntity user, UserStatus userStatus) {
        UserStatusHistoryEntity entity = new UserStatusHistoryEntity();
        entity.user = user;
        entity.userStatus = userStatus;

        return entity;
    }
}
