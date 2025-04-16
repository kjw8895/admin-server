package com.admin.core.domain;

import com.admin.common.code.TermsType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TERMS")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TermsEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attachment_file_id")
    private AttachmentFileEntity attachmentFile;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TermsType type;

    public static TermsEntity toEntity(TermsType type, AttachmentFileEntity attachmentFile) {
        TermsEntity entity = new TermsEntity();
        entity.attachmentFile = attachmentFile;
        entity.type = type;

        return entity;
    }

}
