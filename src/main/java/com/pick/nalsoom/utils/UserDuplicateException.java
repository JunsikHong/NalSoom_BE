package com.pick.nalsoom.utils;

public class UserDuplicateException extends RuntimeException {
    private static final long serialVersionUID = 6453502458182393432L;
    public UserDuplicateException(String message) {
        super(message);
    }
}
