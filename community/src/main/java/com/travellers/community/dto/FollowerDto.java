package com.travellers.community.dto;

import com.travellers.community.model.User;
import lombok.Data;

@Data
public class FollowerDto {
    private User follower;
    private User followedby;
}
