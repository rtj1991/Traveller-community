package com.travellers.community.dto;

import lombok.Data;

@Data
public class TripDto {
    private Integer id;
    private String location;
    private String description;
    private String startDate;
    private String endDate;
    private int status;
}
