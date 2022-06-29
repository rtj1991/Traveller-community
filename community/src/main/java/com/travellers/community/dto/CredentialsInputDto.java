package com.travellers.community.dto;

import lombok.Data;

@Data
public class CredentialsInputDto {
    private String first_name;
    private String last_name;
    private String nic;
    private String email;
    private String username;
    private String password;
    private Boolean enabled;
}
