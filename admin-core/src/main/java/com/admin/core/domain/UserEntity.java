package com.admin.core.domain;

import com.admin.common.code.UserStatus;
import com.admin.core.application.command.UserCommand;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "USER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Embedded
    private UserName name;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private UserStatus status = UserStatus.ACTIVATION;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRoleEntity> roles = new HashSet<>();

    public Set<RoleEntity> getRoleEntities() {
        return this.roles.stream().map(UserRoleEntity::getRole).collect(Collectors.toSet());
    }

    public void addRole(RoleEntity role) {
        this.roles.add(new UserRoleEntity(this, role));
    }

    public void update(UserCommand command) {
        this.phone = command.getPhone() == null ? this.phone : command.getPhone();
    }

    public void suspend() {
        this.status = UserStatus.SUSPENDED;
    }

    public static UserEntity toEntity(UserCommand command, String encryptedPassword) {
        UserEntity entity = new UserEntity();
        entity.email = command.getEmail();
        entity.password = encryptedPassword;
        entity.phone = command.getPhone();
        entity.name = UserName.of(command.getFirstName(), command.getLastName());

        return entity;
    }
}
