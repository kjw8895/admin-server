package com.admin.api.master.service;

import com.admin.api.master.application.dto.TermsDto;
import com.admin.common.code.TermsType;
import com.admin.core.application.condition.TermsSearchCondition;
import com.admin.core.application.vo.TermsVo;
import com.admin.core.domain.AttachmentFileEntity;
import com.admin.core.domain.TermsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TermsService {
    List<TermsEntity> findAllByType(TermsType type);
    Page<TermsVo> page(Pageable pageable, TermsSearchCondition condition);
    TermsEntity create(TermsDto.Request request, AttachmentFileEntity attachmentFile);
}
