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
    public String login(@InputArgument("username") String username, @InputArgument("password") String password) {
        User user = userRepository.findByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new GraphQLException("Invalid credentials");
        }
        String build = tokenGenerator.build(user.getUsername(), user.getRoles());
        return build;
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
    @PreAuthorize("hasAuthority('ADMIN')")
    @DgsData(parentType = "Mutation", field = "getUser")
    public List<User> getUser(@InputArgument("id") String id) {
        System.out.println("id---> "+userRepository.findById(Integer.valueOf(id)).get());
        return userRepository.findAll();
//        return "new User()-----> ";
    }
}
