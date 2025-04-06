package com.admin.core.application.command;

import com.admin.common.code.RoleType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCommand {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private final RoleType roleType = RoleType.ROLE_USER;

    public UserCommand(String email, String password, String firstName, String lastName, String phone) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
    }
}
