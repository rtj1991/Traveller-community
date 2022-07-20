package com.travellers.community.exceptions;

public class TripNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public TripNotFoundException() {
        super("Trip not found");
    }
}
