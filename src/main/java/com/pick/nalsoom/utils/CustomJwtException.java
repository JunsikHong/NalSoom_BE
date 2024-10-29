package com.pick.nalsoom.utils;

public class CustomJwtException extends RuntimeException{

    private static final long serialVersionUID = -7398946461243758775L;

    public CustomJwtException(String message) {
        super(message);
    }
}
