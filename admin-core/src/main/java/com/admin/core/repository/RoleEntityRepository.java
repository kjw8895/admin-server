package com.admin.core.repository;

import com.admin.common.code.RoleType;
import com.admin.core.domain.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByType(RoleType type);
}
