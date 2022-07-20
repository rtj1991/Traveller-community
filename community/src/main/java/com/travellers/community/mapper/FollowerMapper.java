package com.travellers.community.mapper;

import com.travellers.community.dto.FollowerDto;
import com.travellers.community.model.Follower;
import com.travellers.community.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "Spring")
public interface FollowerMapper {
    FollowerMapper FOLLOWER_MAPPER= Mappers.getMapper(FollowerMapper.class);

    Follower modelToDto(FollowerDto followerDto);
}
