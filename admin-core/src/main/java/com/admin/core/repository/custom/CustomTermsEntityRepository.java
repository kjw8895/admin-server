package com.admin.core.repository.custom;

import com.admin.core.application.condition.TermsSearchCondition;
import com.admin.core.application.vo.TermsVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomTermsEntityRepository {
    Page<TermsVo> page(Pageable pageable, TermsSearchCondition condition);
}
