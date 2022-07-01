package com.travellers.community.config.jwt;

import com.travellers.community.config.TokenPayload;
import com.travellers.community.config.TokenUtils;
import com.travellers.community.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final TokenUtils tokenUtils;

    public JWTAuthorizationFilter(AuthenticationManager authManager, TokenUtils tokenUtils) {
        super(authManager);
        this.tokenUtils = tokenUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        System.out.println("tokenUtils-----> " + tokenUtils);
        String header = req.getHeader(tokenUtils.getHeaderString());
        if (header == null || !header.startsWith(tokenUtils.getTokenPrefix())) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            TokenPayload tokenPayload = tokenUtils.decodeToken(token);
            if (tokenPayload.getUsername() != null) {
                return new UsernamePasswordAuthenticationToken(tokenPayload.getUsername(), null, buildAutorities(tokenPayload.getRole()));
            }
            LOGGER.error("Valid token contains no user info");
        }
        return null;
    }

    private List<GrantedAuthority> buildAutorities(List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return authorities;
    }
}
