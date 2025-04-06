package com.admin.api.master.controller;

import com.admin.api.master.application.dto.UserDto;
import com.admin.api.master.application.dto.UserSearchRequest;
import com.admin.api.master.facade.UserFacade;
import com.admin.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {
    private final UserFacade facade;

    @GetMapping(value = "/page")
    public ResponseEntity<CommonResponse<Page<UserDto>>> page(@RequestParam(required = false) UserSearchRequest request, Pageable pageable) {
        Page<UserDto> result = facade.page(pageable, request);
        return CommonResponse.ok(result);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CommonResponse<UserDto>> fetchById(@PathVariable Long id) {
        UserDto result = facade.fetchById(id);
        return CommonResponse.ok(result);
    }
}
