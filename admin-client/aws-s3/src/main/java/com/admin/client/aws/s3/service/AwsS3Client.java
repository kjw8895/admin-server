package com.admin.client.aws.s3.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;

import java.io.InputStream;

public interface AwsS3Client {
    void putObject(String bucketName, String key, InputStream file, ObjectMetadata metadata);
    S3Object getObject(String bucketName, String key);
}
