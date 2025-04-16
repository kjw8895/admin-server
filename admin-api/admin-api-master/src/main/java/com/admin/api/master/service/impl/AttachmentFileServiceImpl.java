package com.admin.api.master.service.impl;

import com.admin.api.master.service.AttachmentFileService;
import com.admin.client.aws.s3.config.property.AwsS3Properties;
import com.admin.client.aws.s3.service.AwsS3Client;
import com.admin.common.utils.DefaultDateTimeFormatUtils;
import com.admin.core.domain.AttachmentFileEntity;
import com.admin.core.repository.AttachmentFileEntityRepository;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties({AwsS3Properties.class})
@Slf4j
public class AttachmentFileServiceImpl implements AttachmentFileService {
    private final AttachmentFileEntityRepository attachmentFileEntityRepository;
    private final AwsS3Client awsS3Client;
    private final AwsS3Properties awsS3Properties;

    @Override
    public Optional<AttachmentFileEntity> findById(Long id) {
        return attachmentFileEntityRepository.findById(id);
    }

    @Override
    @Transactional
    public AttachmentFileEntity create(MultipartFile file) {
        try {
            String timestamp = LocalDateTime.now().format(DefaultDateTimeFormatUtils.DATE_TIME_FILE_NAME_FORMAT);
            String fileName = String.format("%s_%s", timestamp, file.getOriginalFilename());

            AttachmentFileEntity attachmentFile = AttachmentFileEntity.toEntity(fileName, file.getOriginalFilename(), awsS3Properties.getPath(fileName));
            attachmentFileEntityRepository.save(attachmentFile);

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());
            awsS3Client.putObject(awsS3Properties.getBucket(), awsS3Properties.getPath(fileName), file.getInputStream(), metadata);

            return attachmentFile;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("file creation failed");
        }
    }
}
