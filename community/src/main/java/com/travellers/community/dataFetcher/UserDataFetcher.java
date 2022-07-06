package com.travellers.community.dataFetcher;

import com.netflix.graphql.dgs.*;
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
    public Boolean createLoginUser(UserDto userInfo) {
        User byEmail = userRepository.findByEmail(userInfo.getEmail());
        if (byEmail == null) {
            userService.saveUser(userInfo);
            return true;
        } else {
            return false;
        }
    }

    //    @PreAuthorize("hasAnyRole()")
    @DgsQuery
    public User getUser(@InputArgument("id") String id) {
        return userService.findUserById(id);
    }

    @DgsQuery
    public List<User> getAllUser() {
        return userService.findAllUsers();
    }

    @DgsMutation
    public User editLoginUser(@InputArgument("id") int id, UserDto userInfo) {
        return userService.editUser(id, userInfo);
    }

    @DgsQuery
    public Boolean upgradeUser(@InputArgument("id") int id){
        return userService.upgradePremiumUser(id);
    }
}
