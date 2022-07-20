package com.travellers.community.dto;

import lombok.Data;

@Data
public class UserDto {
    public Integer user_id;
    public String name;
    public String profile_pic;
    public String dob;
    public int gender;
    public String location;
    public String email;
    public String password;
}
