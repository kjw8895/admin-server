package com.admin.api.master.facade.impl;

import com.admin.api.master.application.dto.TermsDto;
import com.admin.api.master.application.dto.TermsSearchRequest;
import com.admin.api.master.facade.TermsFacade;
import com.admin.api.master.service.AttachmentFileService;
import com.admin.api.master.service.TermsService;
import com.admin.client.aws.s3.config.property.AwsS3Properties;
import com.admin.common.code.TermsType;
import com.admin.core.application.condition.TermsSearchCondition;
import com.admin.core.application.vo.TermsVo;
import com.admin.core.domain.AttachmentFileEntity;
import com.admin.core.domain.TermsEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties({AwsS3Properties.class})
@Slf4j
public class TermsFacadeImpl implements TermsFacade {
    private final TermsService termsService;
    private final AttachmentFileService attachmentFileService;
    private final AwsS3Properties awsS3Properties;

    @Override
    @Transactional
    public List<TermsDto.Response> findByType(TermsType type) {
        List<TermsEntity> termsList = termsService.findAllByType(type);

        return termsList.stream()
                .map(e -> TermsDto.Response.toResponse(e.getId(), e.getType(), e.getAttachmentFile().getFullUrl(awsS3Properties.getBaseUrl())))
                .toList();
    }

    @Override
    public Page<TermsDto.Response> page(Pageable pageable, TermsSearchRequest request) {
        TermsSearchCondition condition = request == null ? new TermsSearchCondition() : request.toCondition();
        Page<TermsVo> page = termsService.page(pageable, condition);
        return page.map(vo -> TermsDto.Response.toResponse(vo.getTerms().getId(), vo.getTerms().getType(), vo.getAttachmentFile().getUrl()));
    }

    @Override
    public TermsDto.Response create(TermsDto.Request request, MultipartFile file) {
        try {
            AttachmentFileEntity attachmentFile = attachmentFileService.create(file);
            TermsEntity terms = termsService.create(request, attachmentFile);
            return TermsDto.Response.toResponse(terms.getId(), terms.getType(), attachmentFile.getUrl());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("Failed to create terms");
        }
    }
}

