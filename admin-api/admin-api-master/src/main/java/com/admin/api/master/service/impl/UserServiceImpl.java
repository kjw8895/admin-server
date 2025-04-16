package com.admin.api.master.service.impl;

import com.admin.api.master.service.UserService;
import com.admin.core.application.condition.UserSearchCondition;
import com.admin.core.application.vo.UserVo;
import com.admin.core.domain.UserEntity;
import com.admin.core.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserEntityRepository userEntityRepository;

    @Override
    public Page<UserVo> page(Pageable pageable, UserSearchCondition condition) {
        return userEntityRepository.page(pageable, condition);
    }

    @Override
    public Optional<UserEntity> fetchById(Long id) {
        return userEntityRepository.findById(id);
    }
}
