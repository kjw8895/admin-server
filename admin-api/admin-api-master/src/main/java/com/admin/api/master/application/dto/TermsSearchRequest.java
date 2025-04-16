package com.admin.api.master.application.dto;

import com.admin.common.code.TermsType;
import com.admin.core.application.condition.TermsSearchCondition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TermsSearchRequest {
    private Long id;
    private TermsType type;

    public TermsSearchCondition toCondition() {
        return new TermsSearchCondition(id, type);
    }
}
