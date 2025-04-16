package com.admin.core.repository.custom;

import com.admin.core.application.condition.UserSearchCondition;
import com.admin.core.application.vo.UserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomUserEntityRepository {
    Page<UserVo> page(Pageable pageable, UserSearchCondition condition);
}
