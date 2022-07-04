package com.travellers.community.dataFetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import com.travellers.community.config.jwt.JWTTokenGenerator;
import com.travellers.community.dto.CredentialsInputDto;
import com.travellers.community.model.User;
import com.travellers.community.repository.UserRepository;
import graphql.GraphQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @DgsData(parentType = "Mutation", field = "login")
    public String login(@InputArgument("email") String email, @InputArgument("password") String password) {
        User user = userRepository.findByEmail(email);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new GraphQLException("Invalid credentials");
        }
        String build = tokenGenerator.build(user.getEmail(), user.getRoles());
        return build;
    }

    @DgsData(parentType = "Mutation", field = "createUser")
    public User createUser(CredentialsInputDto credentials) {
        User user = new User();
        user.setName(credentials.getName());
        user.setProfile_pic(credentials.getProfile_pic());
        user.setDob(credentials.getDob());
        user.setGender(credentials.getGender());
        user.setLocation(credentials.getLocation());
        user.setEmail(credentials.getEmail());
        user.setPassword(credentials.getPassword());
        return userRepository.save(user);
    }

//    @PreAuthorize("hasAnyRole()")
    @DgsData(parentType = "Query", field = "getUser")
    public List<User> getUser(@InputArgument("id") String id) {
        System.out.println("id---> "+userRepository.findById(Integer.valueOf(id)).get());
        return userRepository.findAll();
//        return "new User()-----> ";
    }
}
