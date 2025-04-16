package com.admin.api.master.controller;

import com.admin.api.master.application.dto.TermsDto;
import com.admin.api.master.application.dto.TermsSearchRequest;
import com.admin.api.master.facade.TermsFacade;
import com.admin.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/terms")
@RequiredArgsConstructor
public class TermsController {
    private final TermsFacade facade;

    @GetMapping(value = "/page")
    public ResponseEntity<CommonResponse<Page<TermsDto.Response>>> page(Pageable pageable, TermsSearchRequest request) {
        Page<TermsDto.Response> page = facade.page(pageable, request);
        return CommonResponse.ok(page);
    }

    @PostMapping
    public ResponseEntity<CommonResponse<TermsDto.Response>> create(@RequestPart("request") TermsDto.Request request, @RequestPart("file") MultipartFile file) {
        TermsDto.Response response = facade.create(request, file);
        return CommonResponse.ok(response);
    }
}
