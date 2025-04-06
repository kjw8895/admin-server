package com.admin.api.master.facade.impl;

import com.admin.api.master.application.dto.UserDto;
import com.admin.api.master.application.dto.UserSearchRequest;
import com.admin.api.master.facade.UserFacade;
import com.admin.api.master.service.UserService;
import com.admin.common.code.CommonExceptionCode;
import com.admin.common.exception.CommonException;
import com.admin.core.application.condition.UserSearchCondition;
import com.admin.core.application.vo.UserVo;
import com.admin.core.domain.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UserFacadeImpl implements UserFacade {
    private final UserService userService;

    @Transactional(readOnly = true)
    @Override
    public Page<UserDto> page(Pageable pageable, UserSearchRequest request) {
        UserSearchCondition condition = request == null ? new UserSearchCondition() : request.toCondition();
        Page<UserVo> page = userService.page(pageable, condition);
        return page.map(vo -> UserDto.toDto(vo.getUser(), vo.getRoles()));
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto fetchById(Long id) {
        UserEntity user = userService.fetchById(id).orElseThrow(() -> new CommonException(CommonExceptionCode.NOT_FOUND_RESOURCE));
        return UserDto.toDto(user);
    }
}
