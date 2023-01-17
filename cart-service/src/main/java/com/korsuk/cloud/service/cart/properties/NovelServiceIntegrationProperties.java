package com.korsuk.cloud.service.cart.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "integrations.book-service")
@Data
public class NovelServiceIntegrationProperties {

    private String url;
    private NovelServiceIntegrationTimeoutProperties timeout;
}
