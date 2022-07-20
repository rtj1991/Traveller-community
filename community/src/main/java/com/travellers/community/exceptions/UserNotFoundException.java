package com.travellers.community.exceptions;

public class UserNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public UserNotFoundException() {
         super("User Not Found");
    }

}
