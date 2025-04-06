package com.admin.api.master.application.dto;

import com.admin.core.application.condition.UserSearchCondition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchRequest {
    private Long id;
    private String email;
    private String name;

    public UserSearchCondition toCondition() {
        return new UserSearchCondition(id, email, name);
    }
}
