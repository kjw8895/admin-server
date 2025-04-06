package com.admin.core.application.vo;

import com.admin.core.domain.RoleEntity;
import com.admin.core.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserVo {
    private UserEntity user;
    private List<RoleEntity> roles;
}
