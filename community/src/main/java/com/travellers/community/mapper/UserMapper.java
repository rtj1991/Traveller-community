package com.travellers.community.mapper;

import com.travellers.community.dto.UserDto;
import com.travellers.community.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "Spring")
public interface UserMapper {
    UserMapper USER_MAPPER=Mappers.getMapper(UserMapper.class);

    User modelToDto(UserDto userDto);
    UserDto dtoToModel(User user);
}
