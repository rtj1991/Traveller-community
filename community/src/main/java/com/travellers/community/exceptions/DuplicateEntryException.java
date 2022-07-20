package com.travellers.community.exceptions;

public class DuplicateEntryException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public DuplicateEntryException() {
        super("Duplicate Entry Found");
    }
}
