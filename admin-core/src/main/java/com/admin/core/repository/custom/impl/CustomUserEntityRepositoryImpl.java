package com.admin.core.repository.custom.impl;

import com.admin.core.application.condition.UserSearchCondition;
import com.admin.core.application.vo.UserVo;
import com.admin.core.domain.*;
import com.admin.core.repository.custom.CustomUserEntityRepository;
import com.admin.core.utils.CustomQuerydslUtils;
import com.querydsl.core.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CustomUserEntityRepositoryImpl extends QuerydslRepositorySupport implements CustomUserEntityRepository {

    private static final QUserEntity USER = QUserEntity.userEntity;
    private static final QUserRoleEntity USER_ROLE = QUserRoleEntity.userRoleEntity;
    private static final QRoleEntity ROLE = QRoleEntity.roleEntity;

    public CustomUserEntityRepositoryImpl() {
        super(UserEntity.class);
    }

    @Override
    public Page<UserVo> page(Pageable pageable, UserSearchCondition condition) {
        List<Long> userIds = from(USER)
                .join(USER.roles, USER_ROLE)
                .join(USER_ROLE.role, ROLE)
                .where(CustomQuerydslUtils.where()
                        .optionalAnd(condition.getEmail(), () -> USER.email.containsIgnoreCase(condition.getEmail()))
                        .optionalAnd(condition.getName(), () -> USER.name.firstName.concat(USER.name.lastName).containsIgnoreCase(condition.getName())
                                .or(USER.name.lastName.concat(USER.name.firstName).containsIgnoreCase(condition.getName())))
                )
                .select(USER.id)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        if (userIds.isEmpty()) {
            return Page.empty(pageable);
        }


        // 2. ID 기준으로 전체 fetch + join fetch
        List<Tuple> tuples =
                from(USER)
                        .join(USER.roles, USER_ROLE).fetchJoin()
                        .join(USER_ROLE.role, ROLE).fetchJoin()
                        .where(USER.id.in(userIds))
                        .select(USER, ROLE)
                        .fetch();

        Map<UserEntity, List<RoleEntity>> grouped = tuples.stream()
                .collect(Collectors.groupingBy(
                        t -> t.get(USER),
                        Collectors.mapping(t -> t.get(ROLE), Collectors.toList())
                ));

        List<UserVo> content = grouped.entrySet().stream()
                .map(e -> new UserVo(e.getKey(), e.getValue()))
                .toList();

        Long total = from(USER)
                .where(CustomQuerydslUtils.where()
                        .optionalAnd(condition.getEmail(), () -> USER.email.containsIgnoreCase(condition.getEmail()))
                        .optionalAnd(condition.getName(), () ->
                                USER.name.firstName.concat(USER.name.lastName).containsIgnoreCase(condition.getName())
                                        .or(USER.name.lastName.concat(USER.name.firstName).containsIgnoreCase(condition.getName()))
                        ))
                .select(USER.count())
                .fetchOne();

        return CustomQuerydslUtils.page(content, pageable, total);
    }
}
