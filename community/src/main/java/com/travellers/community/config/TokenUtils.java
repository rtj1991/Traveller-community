package com.travellers.community.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

public abstract class TokenUtils {

    @Value("${header-name}")
    private String headerString;

    @Value("${prefix}")
    private String tokenPrefix;

    @Value("${secret-password}")
    private String secret;

    @Value("${duration-ms}")
    private long expirationTime;

    public String getHeaderString() {
        return headerString;
    }

    public void setHeaderString(String headerString) {
        this.headerString = headerString;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public abstract TokenPayload decodeToken(String authorizationHeader);
}
