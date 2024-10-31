package com.pick.nalsoom.utils;

public class NoSuchReviewException extends RuntimeException {

    private static final long serialVersionUID = 9203002048432976610L;
    public NoSuchReviewException(String message) {
        super(message);
    }
}
