package edu.snhu.airbook.config;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

/**
 * Amadeus Api properties config
 */
@ConfigurationProperties(prefix = "amadeus")
@Configuration
@Validated
@Data
public class AmadeusApiProperties {
    @NotNull
    private String apiKey;
    @NotNull
    private String apiSecret;
}
