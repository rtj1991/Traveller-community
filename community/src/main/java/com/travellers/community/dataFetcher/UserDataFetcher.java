package com.travellers.community.dataFetcher;

import com.netflix.graphql.dgs.*;
import com.travellers.community.config.TokenProvider;
import com.travellers.community.dto.UserDto;
import com.travellers.community.exceptions.DuplicateEntryException;
import com.travellers.community.exceptions.UserNotFoundException;
import com.travellers.community.mapper.UserMapper;
import com.travellers.community.model.User;
import com.travellers.community.repository.UserRepository;
import com.travellers.community.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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

    @Autowired
    private UserMapper userMapper;

    @PreAuthorize("isAnonymous()")
    @DgsData(parentType = "Mutation", field = "login")
    public String login(@InputArgument("email") String email, @InputArgument("password") String password) {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return token;
    }

    @PreAuthorize("isAnonymous()")
    @DgsMutation(field = "createLoginUser")
    public Boolean createLoginUser(@InputArgument UserDto userInfo) {
        User user_ = userRepository.findByEmail(userInfo.getEmail());
        if (user_ == null) {
            User user = userMapper.modelToDto(userInfo);
            try {
                userService.saveUser(user);
            } catch (DuplicateEntryException e) {
                throw new DuplicateEntryException();
            }
            return true;
        } else {
            return false;
        }
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','PREMIUM')")
    @DgsQuery(field = "getUser")
    public User getUser(@InputArgument("id") String id) {
        try {
            return userService.findUserById(id);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','PREMIUM')")
    @DgsQuery
    public List<User> getAllUser() {
        return userService.findAllUsers();
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','PREMIUM')")
    @DgsMutation(field = "editLoginUser")
    public User editLoginUser(@InputArgument("id") int id, @InputArgument UserDto userInfo) {
        User user_ = userRepository.findById(id).get();
        if (user_ == null) throw new UserNotFoundException();
        User user = userMapper.modelToDto(userInfo);
        user.setUser_id(id);
        return userService.editUser(user);

    }

    @PreAuthorize("hasAnyRole('USER','ADMIN','PREMIUM')")
    @DgsQuery(field = "upgradeUser")
    public Boolean upgradeUser(@InputArgument("id") int id) {
        User user_ = userRepository.findById(id).get();
        if (user_ == null) throw new UserNotFoundException();
        try {
            return userService.upgradePremiumUser(id);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }
}
