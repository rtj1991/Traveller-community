package com.travellers.community.dataFetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.travellers.community.dto.CredentialsInputDto;
import com.travellers.community.model.User;
import com.travellers.community.repository.UserRepository;
import com.travellers.community.service.TravellerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@DgsComponent
public class UserDataFetcher {

    @Autowired
    private TravellerUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private UserRepository userRepository;

    @PreAuthorize("isAnonymous()")
    @DgsData(parentType = "Mutation", field = "login")
    public User login(CredentialsInputDto credentials) {
        String username = credentials.getUsername();
        String password = credentials.getPassword();
        UsernamePasswordAuthenticationToken credential = new UsernamePasswordAuthenticationToken(password, password);
        try {
            SecurityContextHolder.getContext().setAuthentication(authenticationProvider.authenticate(credential));
            return userDetailsService.getCurrentUser();
        } catch (AuthenticationException ex) {
            throw new UsernameNotFoundException(username);
        }
    }

    @DgsData(parentType = "Mutation", field = "createUser")
    public User createUser(CredentialsInputDto credentials) {
        User user = new User();
        user.setFirst_name(credentials.getFirst_name());
        user.setLast_name(credentials.getLast_name());
        user.setNic(credentials.getNic());
        user.setEmail(credentials.getEmail());
        user.setUsername(credentials.getUsername());
        user.setPassword(credentials.getPassword());
        user.setEnabled(credentials.getEnabled());
        return userRepository.save(user);
    }
}
