package com.example.myfashionblog.exception;

public class PostNotAvailableException extends Exception{
    private String message;

    public PostNotAvailableException(String message) {  //NOTE: You have to use the regular constructor with exceptions, NOT LOMBOK!!
        this.message = message;
    }
}
