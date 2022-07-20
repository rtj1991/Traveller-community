package com.travellers.community.exceptions;

public class DuplicateEntryException extends RuntimeException{
    public DuplicateEntryException(Throwable throwable){
        super(throwable);
    }
}
