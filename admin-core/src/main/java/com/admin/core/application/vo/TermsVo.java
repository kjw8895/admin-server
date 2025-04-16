package com.admin.core.application.vo;

import com.admin.core.domain.AttachmentFileEntity;
import com.admin.core.domain.TermsEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TermsVo {
    private TermsEntity terms;
    private AttachmentFileEntity attachmentFile;
}
