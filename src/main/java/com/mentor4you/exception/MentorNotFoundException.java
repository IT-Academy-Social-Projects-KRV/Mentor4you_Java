package com.mentor4you.exception;

import com.mentor4you.model.Categories;

public class MentorNotFoundException extends RuntimeException{

    public MentorNotFoundException(String message){
        super(message);
    }

    public static class UnkownIdentifierException {
    }
}
