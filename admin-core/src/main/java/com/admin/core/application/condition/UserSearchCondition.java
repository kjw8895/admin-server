package com.admin.core.application.condition;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchCondition {
    private Long id;
    private String email;
    private String name;
}
