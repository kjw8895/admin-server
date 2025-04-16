package com.admin.core.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ATTACHMENT_FILE")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttachmentFileEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "origin_file_name")
    private String originFileName;

    @Column(name = "resource_url")
    private String url;

    public String getFullUrl(String baseUrl) {
        return String.format("%s/%s", baseUrl, url);
    }

    public static AttachmentFileEntity toEntity(String fileName, String originFileName, String url) {
        AttachmentFileEntity entity = new AttachmentFileEntity();
        entity.fileName = fileName;
        entity.originFileName = originFileName;
        entity.url = url;

        return entity;
    }
}

