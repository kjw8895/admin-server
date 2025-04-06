package com.admin.api.master.config;

import com.admin.api.common.config.WebMvcConfig;
import com.admin.client.redisson.config.RedissonConfig;
import com.admin.core.config.AdminCoreConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AdminCoreConfig.class, WebMvcConfig.class, RedissonConfig.class})
@ComponentScan(value = {
        "com.admin.client.redisson",
        "com.admin.api.common"
})
public class AdminApiMasterConfig {
}
