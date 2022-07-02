package com.travellers.community.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.travellers.community.config.TokenPayload;
import com.travellers.community.config.TokenUtils;
import com.travellers.community.model.Role;
import org.springframework.stereotype.Component;

@Component
public class JWTTokenUtils extends TokenUtils {

    public TokenPayload decodeToken(String authorizationHeader) {
        DecodedJWT decodedToken = JWT.require(Algorithm.HMAC512(getSecret().getBytes()))
                .build()
                .verify(authorizationHeader.replace(getTokenPrefix(), ""));

        return new TokenPayload(decodedToken.getSubject(), decodedToken.getClaim("role").as(Role.class));
    }
}
