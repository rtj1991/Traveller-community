package com.travellers.community.dto;

import lombok.Data;

@Data
public class CredentialsInputDto {
    private String name;
    private String profile_pic;
    public String dob;
    public String gender;
    public String location;
    private String email;
    private String password;
}
