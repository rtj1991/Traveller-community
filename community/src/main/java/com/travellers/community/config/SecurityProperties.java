package com.travellers.community.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Component;

import java.time.Duration;

@ConstructorBinding
@Component
@ConfigurationProperties(prefix = "community.config")
@Getter
@RequiredArgsConstructor
public class SecurityProperties {

    private final int passwordStrength;

    private final String tokenSecret;

    private final String tokenIssuer = "travellers";

    private final Duration tokenExpiration = Duration.ofHours(4);
}
