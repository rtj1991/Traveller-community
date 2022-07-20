package com.travellers.community.mapper;

import com.travellers.community.dto.ReviewDto;
import com.travellers.community.model.Review;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "Spring")
public interface ReviewMapper {
    ReviewMapper REVIEW_MAPPER= Mappers.getMapper(ReviewMapper.class);

    Review modelTDto(ReviewDto reviewDto);
}
