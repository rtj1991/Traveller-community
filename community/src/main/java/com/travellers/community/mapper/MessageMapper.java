package com.travellers.community.mapper;

import com.travellers.community.dto.MessageDto;
import com.travellers.community.model.Message;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "Spring")
public interface MessageMapper {
    MessageMapper MESSAGE_MAPPER= Mappers.getMapper(MessageMapper.class);

    Message modelToDto(MessageDto messageDto);
}
