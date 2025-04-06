package com.admin.api.master.facade;

import com.admin.api.master.application.dto.UserDto;
import com.admin.api.master.application.dto.UserSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserFacade {
    Page<UserDto> page(Pageable pageable, UserSearchRequest request);
    UserDto fetchById(Long id);
}
