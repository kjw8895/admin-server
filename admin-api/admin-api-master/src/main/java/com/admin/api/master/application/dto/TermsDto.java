package com.admin.api.master.application.dto;

import com.admin.common.code.TermsType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public abstract class TermsDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor
    public static class Request {
        private TermsType type;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private Long id;
        private TermsType type;
        private String resourceUrl;

        public static Response toResponse(Long id, TermsType type, String resourceUrl) {
            return new Response(id, type, resourceUrl);
        }
    }
}
