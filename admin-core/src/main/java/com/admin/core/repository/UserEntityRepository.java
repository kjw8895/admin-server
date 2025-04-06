package com.admin.core.repository;

import com.admin.core.domain.UserEntity;
import com.admin.core.repository.custom.CustomUserEntityRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long>, CustomUserEntityRepository {
    Optional<UserEntity> findByEmail(String email);
}
