package com.admin.core.repository;

import com.admin.common.code.TermsType;
import com.admin.core.domain.TermsEntity;
import com.admin.core.repository.custom.CustomTermsEntityRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TermsEntityRepository extends JpaRepository<TermsEntity, Long>, CustomTermsEntityRepository {
    List<TermsEntity> findAllByType(TermsType type);
}
