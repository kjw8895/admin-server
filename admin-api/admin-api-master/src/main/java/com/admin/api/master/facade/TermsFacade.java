package com.admin.api.master.facade;

import com.admin.api.master.application.dto.TermsDto;
import com.admin.api.master.application.dto.TermsSearchRequest;
import com.admin.common.code.TermsType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TermsFacade {
    List<TermsDto.Response> findByType(TermsType type);
    Page<TermsDto.Response> page(Pageable pageable, TermsSearchRequest request);
    TermsDto.Response create(TermsDto.Request request, MultipartFile file);
}
