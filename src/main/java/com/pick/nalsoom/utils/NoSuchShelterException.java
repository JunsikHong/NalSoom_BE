package com.pick.nalsoom.utils;

public class NoSuchShelterException extends RuntimeException{

    private static final long serialVersionUID = -7099099255740442397L;

    public NoSuchShelterException(String message) {
        super(message);
    }
}
