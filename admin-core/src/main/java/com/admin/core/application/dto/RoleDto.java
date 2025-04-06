package com.admin.core.application.dto;

import com.admin.common.code.RoleType;
import com.admin.core.domain.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {
    private Long id;
    private String name;
    private RoleType type;

    public static RoleDto toDto(RoleEntity entity) {
        RoleDto dto = new RoleDto();
        dto.id = entity.getId();
        dto.name = entity.getName();
        dto.type = entity.getType();

        return dto;
    }
}
