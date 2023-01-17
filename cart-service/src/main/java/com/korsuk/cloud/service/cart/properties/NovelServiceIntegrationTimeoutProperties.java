package com.korsuk.cloud.service.cart.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "integrations.book-service.timeout")
@Data
public class NovelServiceIntegrationTimeoutProperties {

    private Integer connect;
    private Integer read;
    private Integer write;
}
