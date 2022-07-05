package com.travellers.community.service.user;

import com.travellers.community.dto.UserDto;
import com.travellers.community.model.User;

public interface UserService {
    User save(UserDto userDto);
}
