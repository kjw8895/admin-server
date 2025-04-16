package com.admin.client.aws.s3.service.impl;

import com.admin.client.aws.s3.service.AwsS3Client;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Slf4j
@Component
@RequiredArgsConstructor
public class AwsS3ClientImpl implements AwsS3Client {
    private final AmazonS3 amazonS3;

    @Override
    public void putObject(String bucketName, String key, InputStream file, ObjectMetadata metadata) {
        try {
            amazonS3.putObject(new PutObjectRequest(bucketName, key, file, metadata));
        } catch (Exception e) {
            log.error("error : upload object failed", e);
            throw e;
        }
    }

    @Override
    public S3Object getObject(String bucketName, String key) {
        return amazonS3.getObject(new GetObjectRequest(bucketName, key));
    }
}
