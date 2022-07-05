package com.travellers.community.dataFetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import com.travellers.community.config.jwt.JWTTokenGenerator;
import com.travellers.community.dto.UserDto;
import com.travellers.community.model.User;
import com.travellers.community.repository.UserRepository;
import com.travellers.community.service.user.UserService;
import graphql.GraphQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@DgsComponent
public class UserDataFetcher {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTTokenGenerator tokenGenerator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @DgsData(parentType = "Mutation", field = "login")
    public String login(@InputArgument("email") String email, @InputArgument("password") String password) {
        User user = userRepository.findByEmail(email);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new GraphQLException("Invalid userInfo");
        }
        String build = tokenGenerator.build(user.getEmail(), user.getRoles());
        return build;
    }

    @DgsMutation
    public User createLoginUser(UserDto userInfo) {
        return userService.save(userInfo);
    }

//    @PreAuthorize("hasAnyRole()")
    @DgsData(parentType = "Query", field = "getUser")
    public User getUser(@InputArgument("id") String id) {
        return userRepository.findById(Integer.valueOf(id)).get();
    }
}
