package com.pick.nalsoom.utils;

import javax.naming.AuthenticationException;

public class CustomUserNameNotFoundException extends AuthenticationException {

    private static final long serialVersionUID = 2272444048892308290L;

    public CustomUserNameNotFoundException(String explanation) {
        super(explanation);
    }
}
