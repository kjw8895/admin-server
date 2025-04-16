package com.admin.client.aws.s3.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("aws.s3")
public class AwsS3Properties {
    private Credential credential;
    private String region;
    private String bucket;
    private String attachmentPath;
    private String baseUrl;

    public String getPath(String fileName) {
        return String.format("%s/%s", attachmentPath, fileName);
    }

    @Getter
    @Setter
    public static class Credential {
        private boolean enabled;
        private String accessKey;
        private String secretKey;
    }
}
