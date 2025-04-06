package com.admin.api.master.service;

import com.admin.core.application.condition.UserSearchCondition;
import com.admin.core.application.vo.UserVo;
import com.admin.core.domain.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserService {
    Page<UserVo> page(Pageable pageable, UserSearchCondition condition);
    Optional<UserEntity> fetchById(Long id);
}
