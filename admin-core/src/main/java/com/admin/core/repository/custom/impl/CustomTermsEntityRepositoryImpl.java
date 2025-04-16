package com.admin.core.repository.custom.impl;

import com.admin.core.application.condition.TermsSearchCondition;
import com.admin.core.application.vo.TermsVo;
import com.admin.core.domain.QAttachmentFileEntity;
import com.admin.core.domain.QTermsEntity;
import com.admin.core.domain.TermsEntity;
import com.admin.core.repository.custom.CustomTermsEntityRepository;
import com.admin.core.utils.CustomQuerydslUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class CustomTermsEntityRepositoryImpl extends QuerydslRepositorySupport implements CustomTermsEntityRepository {
    private static final QTermsEntity TERMS = QTermsEntity.termsEntity;
    private static final QAttachmentFileEntity ATTACHMENT_FILE = QAttachmentFileEntity.attachmentFileEntity;

    public CustomTermsEntityRepositoryImpl() {
        super(TermsEntity.class);
    }

    @Override
    public Page<TermsVo> page(Pageable pageable, TermsSearchCondition condition) {
        JPQLQuery<TermsVo> query = from(TERMS)
                .join(TERMS.attachmentFile, ATTACHMENT_FILE)
                .where(CustomQuerydslUtils.where()
                        .optionalAnd(condition.getType(), () -> TERMS.type.eq(condition.getType()))
                )
                .select(Projections.constructor(TermsVo.class,
                        TERMS,
                        ATTACHMENT_FILE
                ));

        return CustomQuerydslUtils.page(getQuerydsl(), query, pageable);
    }
}
