package com.travellers.community.dataFetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.travellers.community.dto.CredentialsInputDto;
import com.travellers.community.repository.UserRepository;
import com.travellers.community.service.TravellerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;


@DgsComponent
public class UserDataFetcher {

    @Autowired
    private TravellerUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

//    @DgsData(parentType = "Query",field = "retrieve")
    @DgsData(parentType = "Mutation",field = "login")
//    public String getBooks(@InputArgument("username") String username,@InputArgument("password") String password,HttpServletRequest request){
    public UserDetails getUser(CredentialsInputDto credentials){
        String username = credentials.getUsername();
        String password = credentials.getPassword();

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        System.out.println("userDetails----> "+userDetails);
        return userDetails;
    }
}
