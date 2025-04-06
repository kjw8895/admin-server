package com.admin.common.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TermsType implements CodeEnum {
    USER_SIGN_UP_TERMS("USER_SIGN_UP_TERMS");

    private final String text;
}
