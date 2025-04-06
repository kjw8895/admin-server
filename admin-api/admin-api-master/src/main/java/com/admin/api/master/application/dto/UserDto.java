package com.admin.api.master.application.dto;

import com.admin.core.application.dto.RoleDto;
import com.admin.core.application.dto.UserNameDto;
import com.admin.core.domain.RoleEntity;
import com.admin.core.domain.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private UserNameDto name;
    private String email;
    private String phone;
    private List<RoleDto> roles;

    public static UserDto toDto(UserEntity entity, List<RoleEntity> roleEntities) {
        UserDto dto = new UserDto();
        dto.id = entity.getId();
        dto.name = UserNameDto.toDto(entity.getName());
        dto.email = entity.getEmail();
        dto.phone = entity.getPhone();
        dto.roles = roleEntities.stream().map(RoleDto::toDto).toList();

        return dto;
    }

    public static UserDto toDto(UserEntity entity) {
        UserDto dto = new UserDto();
        dto.id = entity.getId();
        dto.name = UserNameDto.toDto(entity.getName());
        dto.email = entity.getEmail();
        dto.phone = entity.getPhone();
        dto.roles = entity.getRoleEntities().stream().map(RoleDto::toDto).toList();

        return dto;
    }
}
