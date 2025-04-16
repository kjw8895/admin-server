package com.admin.api.master.service.impl;

import com.admin.api.master.application.dto.TermsDto;
import com.admin.api.master.service.TermsService;
import com.admin.common.code.TermsType;
import com.admin.core.application.condition.TermsSearchCondition;
import com.admin.core.application.vo.TermsVo;
import com.admin.core.domain.AttachmentFileEntity;
import com.admin.core.domain.TermsEntity;
import com.admin.core.repository.TermsEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TermsServiceImpl implements TermsService {
    private final TermsEntityRepository termsEntityRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TermsEntity> findAllByType(TermsType type) {
        return termsEntityRepository.findAllByType(type);
    }

    @Override
    public Page<TermsVo> page(Pageable pageable, TermsSearchCondition condition) {
        return termsEntityRepository.page(pageable, condition);
    }

    @Override
    @Transactional
    public TermsEntity create(TermsDto.Request request, AttachmentFileEntity attachmentFile) {
        TermsEntity entity = TermsEntity.toEntity(request.getType(), attachmentFile);
        return termsEntityRepository.save(entity);
    }
}
