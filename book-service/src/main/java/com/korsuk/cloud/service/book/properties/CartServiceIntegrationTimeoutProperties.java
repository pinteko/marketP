package com.korsuk.cloud.service.book.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConstructorBinding
@ConfigurationProperties(prefix = "integrations.cart-service.timeout")
@Data
public class CartServiceIntegrationTimeoutProperties {

    private Integer connect;
    private Integer read;
    private Integer write;
}
