package com.travellers.community.service.user;

import com.travellers.community.dto.UserDto;
import com.travellers.community.model.User;

import java.util.List;

public interface UserService {
    User saveUser(UserDto userDto);

    User editUser(int id, UserDto userInfo);

    User findUserById(String id);

    List<User> findAllUsers();

    Boolean upgradePremiumUser(int id);
}
