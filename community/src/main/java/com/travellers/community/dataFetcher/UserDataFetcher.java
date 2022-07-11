package com.travellers.community.dataFetcher;

import com.netflix.graphql.dgs.*;
import com.travellers.community.config.TokenProvider;
import com.travellers.community.dto.UserDto;
import com.travellers.community.model.Role;
import com.travellers.community.model.User;
import com.travellers.community.repository.UserRepository;
import com.travellers.community.service.user.UserService;
import graphql.GraphQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@DgsComponent
public class UserDataFetcher {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PreAuthorize("isAnonymous()")
    @DgsData(parentType = "Mutation", field = "login")
    public String login(@InputArgument("email") String email, @InputArgument("password") String password) {
        System.out.println("loginUser---> " + email);
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
//        return ResponseEntity.ok(new AuthToken(token));

        return token;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','PREMIUM')")
    @DgsMutation
    public Boolean createLoginUser(UserDto userInfo) {
        User byEmail = userRepository.findByEmail(userInfo.getEmail());
        if (byEmail == null) {
            userService.saveUser(userInfo);
            return true;
        } else {
            return false;
        }
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','PREMIUM')")
    @DgsQuery
    public User getUser(@InputArgument("id") String id) {
        return userService.findUserById(id);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','PREMIUM')")
    @DgsQuery
    public List<User> getAllUser() {
        return userService.findAllUsers();
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN','PREMIUM')")
    @DgsMutation
    public User editLoginUser(@InputArgument("id") int id, UserDto userInfo) {
        return userService.editUser(id, userInfo);
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN','PREMIUM')")
    @DgsQuery
    public Boolean upgradeUser(@InputArgument("id") int id) {
        return userService.upgradePremiumUser(id);
    }
}
