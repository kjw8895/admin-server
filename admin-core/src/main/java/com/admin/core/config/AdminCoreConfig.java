package com.admin.core.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(value = {"com.admin.core.domain"})
@Import({QuerydslConfig.class})
@EnableJpaRepositories(value = {"com.admin.core.repository"})
@EnableJpaAuditing
public class AdminCoreConfig {
}
