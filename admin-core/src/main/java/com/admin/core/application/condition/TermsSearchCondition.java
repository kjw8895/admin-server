package com.admin.core.application.condition;

import com.admin.common.code.TermsType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TermsSearchCondition {
    private Long id;
    private TermsType type;
}
