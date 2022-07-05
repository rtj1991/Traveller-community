package com.travellers.community.service.user;

import com.travellers.community.dto.UserDto;
import com.travellers.community.model.User;
import com.travellers.community.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(UserDto userDto) {
        User user = new User();

        user.setName(userDto.getName());
        user.setProfile_pic(userDto.getProfile_pic());
        user.setDob(userDto.getDob());
        user.setGender(userDto.getGender());
        user.setLocation(userDto.getLocation());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }
}
