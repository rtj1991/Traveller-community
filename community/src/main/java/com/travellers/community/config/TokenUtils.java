package com.travellers.community.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;


@Data
public abstract class TokenUtils {

    @Value("${header-name}")
    private String headerString;

    @Value("${prefix}")
    private String tokenPrefix;

    @Value("${secret-password}")
    private String secret;

    @Value("${duration-ms}")
    private long expirationTime;

    public abstract TokenPayload decodeToken(String authorizationHeader);
}
