package com.travellers.community.dto;

import com.travellers.community.model.User;
import lombok.Data;

@Data
public class MessageDto {
    private Integer id;
    private String message;
    private User receiver;
//    private User sender;
}
