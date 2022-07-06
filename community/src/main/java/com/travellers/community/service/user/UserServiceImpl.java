package com.travellers.community.service.user;

import com.travellers.community.dto.UserDto;
import com.travellers.community.model.Role;
import com.travellers.community.model.User;
import com.travellers.community.repository.RoleRepository;
import com.travellers.community.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User saveUser(UserDto userDto) {
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

    @Override
    public User findUserById(String id) {
        return userRepository.findById(Integer.valueOf(id)).get();
    }

    @Override
    public User editUser(int id, UserDto userDto) {
        User user = userRepository.findById(id).get();
        user.setName(userDto.getName());
        user.setProfile_pic(userDto.getProfile_pic());
        user.setDob(userDto.getDob());
        user.setGender(userDto.getGender());
        user.setLocation(userDto.getLocation());
        user.setEmail(userDto.getEmail());
        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Boolean upgradePremiumUser(int id) {
        User user = userRepository.findById(id).get();
        Role premium = roleRepository.findByRole("PREMIUM");
        user.setRoles(Arrays.asList(premium));
        User save = userRepository.save(user);
        if (save!=null){
            return true;
        }else {
            return false;
        }
    }
}
