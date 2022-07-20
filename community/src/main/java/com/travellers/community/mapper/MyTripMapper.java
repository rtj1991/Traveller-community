package com.travellers.community.mapper;

import com.travellers.community.dto.TripDto;
import com.travellers.community.model.MyTrip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface MyTripMapper {
    MyTripMapper MESSAGE_MAPPER= Mappers.getMapper(MyTripMapper.class);

    @Mapping(source = "startDate",
            target = "startDate",
            dateFormat = "yyyy-MM-dd")
    @Mapping(source = "endDate",
            target = "endDate",
            dateFormat = "yyyy-MM-dd")
    MyTrip modelToDto(TripDto tripDto);


}
