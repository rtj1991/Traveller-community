package com.travellers.community.service.user;

import com.travellers.community.dto.UserDto;
import com.travellers.community.model.Role;
import com.travellers.community.model.User;
import com.travellers.community.repository.RoleRepository;
import com.travellers.community.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User saveUser(User user) {
        Role user_role = roleRepository.findByRole("USERS");
        user.addRole(user_role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findUserById(String id) {
        return userRepository.findById(Integer.valueOf(id)).orElse(null);
    }

    @Override
    public User editUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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

        user.removeRole(user.getRoles());
        user.addRole(premium);

        User save = userRepository.save(user);
        if (save != null) {
            return true;
        } else {
            return false;
        }
    }
}
