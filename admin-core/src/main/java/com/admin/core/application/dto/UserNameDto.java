package com.admin.core.application.dto;

import com.admin.core.domain.UserName;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserNameDto {
    private String firstName;
    private String lastName;

    public static UserNameDto toDto(String firstName, String lastName) {
        UserNameDto dto = new UserNameDto();
        dto.firstName = firstName;
        dto.lastName = lastName;

        return dto;
    }

    public static UserNameDto toDto(UserName name) {
        UserNameDto dto = new UserNameDto();
        dto.firstName = name.getFirstName();
        dto.lastName = name.getLastName();

        return dto;
    }
}
