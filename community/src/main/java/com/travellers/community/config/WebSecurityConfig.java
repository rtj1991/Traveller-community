package com.travellers.community.config;

import com.travellers.community.config.jwt.JWTAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Bean
    protected PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    @Bean
    protected AuthenticationManager getAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    protected TokenUtils tokenUtils;
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .headers().frameOptions().disable()
                .and()
                .cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/graphql").permitAll()
                .antMatchers("/graphiql").permitAll()
                .anyRequest().denyAll()
                .and()
                .addFilter(new JWTAuthorizationFilter(getAuthenticationManager(),tokenUtils))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
