package com.admin.core.domain;

import com.admin.common.code.RoleType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ROLE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoleEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Enumerated(value = EnumType.STRING)
    private RoleType type;
}
