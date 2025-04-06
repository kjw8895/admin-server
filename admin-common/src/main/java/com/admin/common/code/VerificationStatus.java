package com.admin.common.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VerificationStatus implements CodeEnum {
    WAITING("WAITING"),
    SUCCESS("SUCCESS");

    private final String text;
}
