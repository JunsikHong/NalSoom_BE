package com.pick.nalsoom.utils;

public class InvalidTokenException extends RuntimeException {
    private static final long serialVersionUID = 7850965263258921402L;
    public InvalidTokenException(String message) {
        super(message);
    }
}
