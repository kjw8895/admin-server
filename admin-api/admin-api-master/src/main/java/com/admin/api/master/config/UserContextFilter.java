package com.admin.api.master.config;

import com.admin.api.common.constant.WebConstant;
import com.admin.common.application.dto.UserInfo;
import com.admin.common.code.CommonExceptionCode;
import com.admin.common.code.RoleType;
import com.admin.common.context.UserContext;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class UserContextFilter extends OncePerRequestFilter {
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return WebConstant.EXCLUDED_END_POINT.stream().anyMatch(pattern -> new AntPathMatcher().match(pattern, path));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String id = request.getHeader("X-User-Id");
            String email = request.getHeader("X-User-Email");
            String status = request.getHeader("X-User-Status");
            String roles = request.getHeader("X-User-Roles");

            if (id != null && email != null) {
                List<String> roleList = Arrays.asList(roles.split(","));
                UserContext.set(new UserInfo(Long.parseLong(id), email, status, roleList.stream().map(RoleType::valueOf).toList()));

                boolean hasRole = roleList.stream()
                        .map(String::trim)
                        .anyMatch(role -> role.equals(RoleType.ROLE_ADMIN.getCode()));

                if (!hasRole) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, CommonExceptionCode.ACCESS_DENIED.getMsg());
                    return;
                }
            }

            filterChain.doFilter(request, response);
        } finally {
            UserContext.clear();
        }
    }
}
