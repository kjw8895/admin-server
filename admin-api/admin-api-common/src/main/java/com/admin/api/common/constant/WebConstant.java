package com.admin.api.common.constant;

import java.util.List;

public class WebConstant {
    public static final List<String> EXCLUDED_END_POINT = List.of(
            "/public/**",
            "/health",
            "/docs/**",
            "/swagger-ui/**"
    );
}
