package com.pick.nalsoom.utils;

public class NoSuchUserException extends RuntimeException {
    private static final long serialVersionUID = -478625476181880159L;
    public NoSuchUserException(String message) {
        super(message);
    }
}
